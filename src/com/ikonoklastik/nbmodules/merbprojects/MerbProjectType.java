/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikonoklastik.nbmodules.merbprojects;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.modules.ruby.spi.project.support.rake.RakeBasedProjectType;
import org.netbeans.modules.ruby.spi.project.support.rake.RakeProjectHelper;

/**
 * Factory for Merb projects.
 * 
 * @author alexbcoles
 */
public class MerbProjectType implements RakeBasedProjectType {
    
    public static final String TYPE = "com.ikonoklastik.nbmodules.merbprojects";
    private static final String PROJECT_CONFIGURATION_NAME = "data"; // NOI18N
    public static final String PROJECT_CONFIGURATION_NAMESPACE = "http://www.netbeans.org/ns/merb-project/1"; // NOI18N
    private static final String PRIVATE_CONFIGURATION_NAME = "data"; // NOI18N
    private static final String PRIVATE_CONFIGURATION_NAMESPACE = "http://www.netbeans.org/ns/merb-project-private/1"; // NOI18N

    /** Do nothing, just a service. **/
    public MerbProjectType() { }
    
    public String getType() {
        return TYPE;
    }

    public Project createProject(RakeProjectHelper helper) throws IOException {
        return new MerbProject(helper);
    }

    public String getPrimaryConfigurationDataElementName(boolean shared) {
        return shared ? PROJECT_CONFIGURATION_NAME : PRIVATE_CONFIGURATION_NAME;
    }

    public String getPrimaryConfigurationDataElementNamespace(boolean shared) {
        return shared ? PROJECT_CONFIGURATION_NAMESPACE : PRIVATE_CONFIGURATION_NAMESPACE;
    }
    
}
