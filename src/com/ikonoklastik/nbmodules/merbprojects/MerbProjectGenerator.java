/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikonoklastik.nbmodules.merbprojects;

import com.ikonoklastik.nbmodules.merbprojects.database.MerbDatabaseConfiguration;
import com.ikonoklastik.nbmodules.merbprojects.ui.customizer.MerbProjectProperties;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.queries.FileEncodingQuery;
import org.netbeans.api.ruby.platform.RubyPlatform;
import org.netbeans.modules.ruby.RubyUtils;
import org.netbeans.modules.ruby.platform.RubyExecution;
import org.netbeans.modules.ruby.platform.execution.DirectoryFileLocator;
import org.netbeans.modules.ruby.platform.execution.ExecutionDescriptor;
import org.netbeans.modules.ruby.platform.execution.ExecutionService;
import org.netbeans.modules.ruby.rubyproject.RakeTargetsAction;
import org.netbeans.modules.ruby.spi.project.support.rake.EditableProperties;
import org.netbeans.modules.ruby.spi.project.support.rake.ProjectGenerator;
import org.netbeans.modules.ruby.spi.project.support.rake.RakeProjectHelper;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.Task;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Creates a Merb project from scratch according to some initial configuration.
 * 
 * @author alexbcoles
 */
public class MerbProjectGenerator {
    
    private MerbProjectGenerator() { }
    
   public static RakeProjectHelper createProject(MerbProjectCreateData data) throws IOException {
       FileObject dirFO = FileUtil.createFolder(data.getDir());
       RubyPlatform platform = data.getPlatform();
       MerbDatabaseConfiguration merbDb = data.getDatabase();
       
       // Run merb-gen to generate the application skeleton
       if (data.isCreate()) {
           final String merbGen = "merb-gen"; //platform.getGemManager().getGemHome() + "merb";
           final File merbGenFile = new File(merbGen);
           final FileObject merbGenFileObj = FileUtil.toFileObject(merbGenFile);
           boolean runThroughRuby = merbGenFileObj != null ? RubyUtils.isRubyFile(merbGenFileObj) : false;
           
           ExecutionDescriptor desc = null;
           String displayName = NbBundle.getMessage(MerbProjectGenerator.class, "GenerateMerb");
           
           String merbDbArg = merbDb.railsGenerationParam() == null ? null : "--database=" + merbDb.railsGenerationParam();
           File pwd = data.getDir().getParentFile();
           if (runThroughRuby) {
               desc = new ExecutionDescriptor(platform, displayName, pwd, merbGen);
               if (merbDbArg != null) {
                   desc.additionalArgs(data.getName(), merbDbArg);
               } else {
                   desc.additionalArgs(data.getName());
               }
           } else {
               desc = new ExecutionDescriptor(platform, displayName, pwd, data.getName());
               if (merbDbArg != null) {
                   desc.additionalArgs(merbDbArg);
               }
               desc.cmd(merbGenFile);
           }
           desc.fileLocator(new DirectoryFileLocator(dirFO));
           //desc.addOutputRecognizer(MERB_GENERATOR);
           ExecutionService service = null;
           if (runThroughRuby) {
                service = new RubyExecution(desc);
           } else {
               // 
                service = new ExecutionService(desc);
           }
           Task task = service.run();
           task.waitFinished();
           
           // Precreate a spec directory if it doesn't exist such that my source root will work
            if (platform.getGemManager().getVersion("rspec") != null) { // NOI18N
                 File spec = new File(data.getDir(), "spec"); // NOI18N
                 if (!spec.exists()) {
                    spec.mkdirs();
                }
            }
           
           dirFO.getFileSystem().refresh(true);
           
       }
       
        RakeProjectHelper h = createProject(dirFO, platform, data); //NOI18N

        Project project = ProjectManager.getDefault().findProject(dirFO);
        merbDb.editConfig((MerbProject) project);

        ProjectManager.getDefault().saveProject(project);

        // Run Rake -T silently to determine the available targets and write into private area
        RakeTargetsAction.refreshTargets(project);

        return h;
    }
   
    private static RakeProjectHelper createProject(FileObject dirFO, RubyPlatform platform, MerbProjectCreateData createData) throws IOException {
        RakeProjectHelper h = ProjectGenerator.createProject(dirFO, MerbProjectType.TYPE);
        Element data = h.getPrimaryConfigurationData(true);
        Document doc = data.getOwnerDocument();
        Element nameEl = doc.createElementNS(MerbProjectType.PROJECT_CONFIGURATION_NAMESPACE, "name");
        nameEl.appendChild(doc.createTextNode(createData.getName()));
        data.appendChild(nameEl);
        
        // set the target server
        EditableProperties privateProperties = h.getProperties(RakeProjectHelper.PRIVATE_PROPERTIES_PATH);
        privateProperties.put(MerbProjectProperties.MERB_SERVERTYPE, createData.getServerInstanceId());
        
        EditableProperties ep = h.getProperties(RakeProjectHelper.PROJECT_PROPERTIES_PATH);
        ep.setProperty(MerbProjectProperties.MERB_PORT, "4000"); // NOI18N
    
        Charset enc = FileEncodingQuery.getDefaultEncoding();
        ep.setProperty(MerbProjectProperties.SOURCE_ENCODING, enc.name());
        
        h.putPrimaryConfigurationData(data, true);
        
        h.putProperties(RakeProjectHelper.PRIVATE_PROPERTIES_PATH, privateProperties);
        h.putProperties(RakeProjectHelper.PROJECT_PROPERTIES_PATH, ep);        
        return h;
    }
    
}
