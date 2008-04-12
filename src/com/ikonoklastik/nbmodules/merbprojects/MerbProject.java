/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikonoklastik.nbmodules.merbprojects;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.modules.ruby.spi.project.support.rake.RakeProjectEvent;
import org.netbeans.modules.ruby.spi.project.support.rake.RakeProjectHelper;
import org.netbeans.modules.ruby.spi.project.support.rake.RakeProjectListener;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;

/**
 *
 * @author alexbcoles
 */
public class MerbProject implements Project, RakeProjectListener {
    // TODO - keep in sync with RubyProject
    public static final String SOURCES_TYPE_RUBY = "ruby"; // NOI18N
    
    protected final RakeProjectHelper helper;
    
    protected MerbProject(RakeProjectHelper helper) throws IOException 
    {
         this.helper = helper;
         
    }
    
    /**
     * Returns the project directory
     * 
     * @return the directory the project is located in
     */
    public FileObject getProjectDirectory() {
        return helper.getProjectDirectory();
    }
    
    @Override
    public String toString() {
        return "MerbProject[" + FileUtil.getFileDisplayName(getProjectDirectory()) + "]"; // NOI18N
    }
    
    public RakeProjectHelper getRakeProjectHelper() {
        return helper;
    }

    public Lookup getLookup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void configurationXmlChanged(RakeProjectEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void propertiesChanged(RakeProjectEvent ev) {
        // currently ignored
    }

}
