/*
 * Copyright (C) 2014, United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 *
 * The Java Pathfinder core (jpf-core) platform is licensed under the
 * Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0. 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package java.security;


/*
 * MJI model class for java.security.AccessController library abstraction
 */
public class AccessController {
  
  public static <T> T doPrivileged (PrivilegedAction<T> action)  {
    return action.run();
  }
  
  public static <T> T doPrivileged (PrivilegedExceptionAction<T> action)
    throws PrivilegedActionException {

    try {
      return action.run();

    } catch (RuntimeException rx){
      throw rx; // we have to let unchecked exceptions pass
    } catch (Exception x) {
      throw new PrivilegedActionException(x);
    }
  }

}

