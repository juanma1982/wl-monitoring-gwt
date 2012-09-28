/*
 * Copyright 2011 Juan Cruz Basso
 * juancruzbasso@gmail.com
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package mBeanControl.interfaces;
/**
 * Threads Interface
 * AccessPoint: {@link IServer}
 * 
 * @author jbrasca
 * date: Aug 2, 2011
 */
public interface IThreads {
	
	public Integer getPendingUserRequestCount();
	
	public Integer getExecuteThreadIdleCount();
}
