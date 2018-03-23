// https://www.ibm.com/support/knowledgecenter/en/SSCKBL_8.5.5/com.ibm.websphere.javadoc.doc/web/apidocs/com/ibm/websphere/management/cmdframework/package-summary.html
package com.salientprocess.bpm;

import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

import teamworks.TWObject;

public class BPM_AdminTaskBeanInfo extends SimpleBeanInfo {
	private Class<BPM_AdminTask> beanClass = BPM_AdminTask.class;

	@Override
	public MethodDescriptor[] getMethodDescriptors() {
		try {
			Method method = beanClass.getMethod("runTask", String.class, TWObject.class);
			if (method == null) {
				System.out.println("Unable to find method.");
				return null;
			}
			ParameterDescriptor parm1 = new ParameterDescriptor();
			parm1.setShortDescription("Command");
			parm1.setDisplayName("Command");
			ParameterDescriptor parm2 = new ParameterDescriptor();
			parm2.setShortDescription("Command Parameters");
			parm2.setDisplayName("Command Parameters");
			MethodDescriptor methodDescriptor = new MethodDescriptor(method,
					new ParameterDescriptor[] { parm1, parm2 });
			return new MethodDescriptor[] { methodDescriptor };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.getMethodDescriptors();
	}
}