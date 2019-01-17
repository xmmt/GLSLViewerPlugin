import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyToolWindow {
    private JPanel myToolWindowContent;
    private JButton showButton;
    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;
    private Renderer renderer;

    public MyToolWindow(ToolWindow toolWindow, Project project) {
        Project pproject = project;
        myToolWindowContent = new JPanel();
        myToolWindowContent.setLayout(new BorderLayout());

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        GLJPanel gljpanel= new GLJPanel( glcapabilities );

        renderer = new Renderer("in vec3 color;\nout vec4 outColor;\nvoid main()\n{\noutColor = vec4(color.x * 0.5, color.y, color.z, 1.0);\n}\n");
        gljpanel.addGLEventListener(renderer);

        myToolWindowContent.add( gljpanel, BorderLayout.CENTER );

        showButton = new JButton("Show");
       showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Editor editor = FileEditorManager.getInstance(pproject).getSelectedTextEditor();
                final Document document = editor.getDocument();
                gljpanel.removeGLEventListener(renderer);
                renderer = new Renderer( document.getText());
                //String text = document.getText();
                //renderer.setFragmentShaderCode(document.getText());
                gljpanel.addGLEventListener(renderer);
            }
       });
        myToolWindowContent.add(showButton, BorderLayout.SOUTH);
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}
