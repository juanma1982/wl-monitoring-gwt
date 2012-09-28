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
package mBeanControl.exceptions;

/**
 *
 * @author jbrasca
 * date: Aug 15, 2011
 */
public class AttributeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	Exception e;
	
	public AttributeNotFoundException(Exception e){
		this.e = e;
	}

   public String toString(){
	   if(e!=null){
		   return "Object not found exception: " + e.toString() ;
	   }
	   return "Object not found exception";
   }  
}
