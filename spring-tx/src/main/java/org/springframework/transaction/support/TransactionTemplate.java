/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.transaction.support;

import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.Assert;

/**
 * Template class that simplifies programmatic transaction demarcation and
 * transaction exception handling.
 *
 * <p>The central method is {@link #execute}, supporting transactional code that
 * implements the {@link TransactionCallback} interface. This template handles
 * the transaction lifecycle and possible exceptions such that neither the
 * TransactionCallback implementation nor the calling code needs to explicitly
 * handle transactions.
 *
 * <p>Typical usage: Allows for writing low-level data access objects that use
 * resources such as JDBC DataSources but are not transaction-aware themselves.
 * Instead, they can implicitly participate in transactions handled by higher-level
 * application services utilizing this class, making calls to the low-level
 * services via an inner-class callback object.
 *
 * <p>Can be used within a service implementation via direct instantiation with
 * a transaction manager reference, or get prepared in an application context
 * and passed to services as bean reference. Note: The transaction manager should
 * always be configured as bean in the application context: in the first case given
 * to the service directly, in the second case given to the prepared template.
 *
 * <p>Supports setting the propagation behavior and the isolation level by name,
 * for convenient configuration in context definitions.
 *
 * @author Juergen Hoeller
 * @since 17.03.2003
 * @see #execute
 * @see #setTransactionManager
 * @see org.springframework.transaction.PlatformTransactionManager
 */
/**
* @Author: wenyixicodedog
* @Date:  2020-07-21
* @Param:
* @return:
* @Description:  编程式事务的事务模板
*/
@SuppressWarnings("serial")
public class TransactionTemplate extends DefaultTransactionDefinition
		implements TransactionOperations, InitializingBean {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Nullable
	private PlatformTransactionManager transactionManager;

	/**
	 * Construct a new TransactionTemplate for bean usage.
	 * <p>Note: The PlatformTransactionManager needs to be set before
	 * any {@code execute} calls.
	 * @see #setTransactionManager
	 */
	public TransactionTemplate() {
	}

	/**
	 * Construct a new TransactionTemplate using the given transaction manager.
	 * @param transactionManager the transaction management strategy to be used
	 */
	public TransactionTemplate(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Construct a new TransactionTemplate using the given transaction manager,
	 * taking its default settings from the given transaction definition.
	 * @param transactionManager the transaction management strategy to be used
	 * @param transactionDefinition the transaction definition to copy the
	 * default settings from. Local properties can still be set to change values.
	 */
	public TransactionTemplate(PlatformTransactionManager transactionManager, TransactionDefinition transactionDefinition) {
		super(transactionDefinition);
		this.transactionManager = transactionManager;
	}

	/**
	 * Set the transaction management strategy to be used.
	 */
	public void setTransactionManager(@Nullable PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Return the transaction management strategy to be used.
	 */
	@Nullable
	public PlatformTransactionManager getTransactionManager() {
		return this.transactionManager;
	}

	/**
	* @Author: wenyixicodedog
	* @Date:  2020-07-21
	* @Param:  []
	* @return:  void
	* @Description:  初始化的时候如果transactionManager为空，则抛出异常(说明transactionManager是在构造的时候注入的或者在构造之后初识化之前被赋值)
	*/
	@Override
	public void afterPropertiesSet() {
		if (this.transactionManager == null) {
			throw new IllegalArgumentException("Property 'transactionManager' is required");
		}
	}

	@Override
	@Nullable
	/**
	* @Author: wenyixicodedog
	* @Date:  2020-07-21
	* @Param:  [action]
	* @return:  T
	* @Description: 1.getTransaction()获取事务
	 * 2.doInTransaction()执行业务逻辑，这里就是用户自定义的业务代码。如果是没有返回值的，就是doInTransactionWithoutResult()
	 * 3.commit()事务提交：调用AbstractPlatformTransactionManager的commit，rollbackOnException()异常回滚：调用AbstractPlatformTransactionManager的rollback()，事务提交回滚
	*/
	public <T> T execute(TransactionCallback<T> action) throws TransactionException {
		Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");

		// 内部封装好的事务管理器(jta相关)
		if (this.transactionManager instanceof CallbackPreferringPlatformTransactionManager) {
			return ((CallbackPreferringPlatformTransactionManager) this.transactionManager).execute(this, action);
		}
		// 需要手动获取事务，执行方法，提交事务的管理器
		// TODO  1.获取事务状态
		else {
			TransactionStatus status = this.transactionManager.getTransaction(this);
			T result;
			try {
				// TODO  2.执行业务逻辑
				result = action.doInTransaction(status);
			}
			catch (RuntimeException | Error ex) {
				// TODO  应用运行时异常 -> 回滚
				rollbackOnException(status, ex);
				throw ex;
			}
			catch (Throwable ex) {
				// Transactional code threw unexpected exception -> rollback
				rollbackOnException(status, ex);
				throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
			}
			// TODO  3.事务提交
			this.transactionManager.commit(status);
			return result;
		}
	}

	/**
	 * Perform a rollback, handling rollback exceptions properly.
	 * @param status object representing the transaction
	 * @param ex the thrown application exception or error
	 * @throws TransactionException in case of a rollback error
	 */
	private void rollbackOnException(TransactionStatus status, Throwable ex) throws TransactionException {
		Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");

		logger.debug("Initiating transaction rollback on application exception", ex);
		try {
			this.transactionManager.rollback(status);
		}
		catch (TransactionSystemException ex2) {
			logger.error("Application exception overridden by rollback exception", ex);
			ex2.initApplicationException(ex);
			throw ex2;
		}
		catch (RuntimeException | Error ex2) {
			logger.error("Application exception overridden by rollback exception", ex);
			throw ex2;
		}
	}

	@Override
	public boolean equals(Object other) {
		return (this == other || (super.equals(other) && (!(other instanceof TransactionTemplate) ||
				getTransactionManager() == ((TransactionTemplate) other).getTransactionManager())));
	}

}
