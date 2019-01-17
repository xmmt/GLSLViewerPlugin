import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;
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
    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;


    public MyToolWindow(ToolWindow toolWindow) {
        myToolWindowContent = new JPanel();
        myToolWindowContent.setLayout(new BorderLayout());

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        GLJPanel gljpanel= new GLJPanel( glcapabilities );

        gljpanel.addGLEventListener(new Renderer());

//        gljpanel.addGLEventListener( new GLEventListener() {
//
//            @Override
//            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
//                //OneTriangle.setup( glautodrawable.getGL().getGL2(), width, height );
//                GL2 gl2 = glautodrawable.getGL().getGL2();
//                gl2.glMatrixMode( GL2.GL_PROJECTION );
//                gl2.glLoadIdentity();
//
//                // coordinate system origin at lower left with width and height same as the window
//                GLU glu = new GLU();
//                glu.gluOrtho2D( 0.0f, width, 0.0f, height );
//
//                gl2.glMatrixMode( GL2.GL_MODELVIEW );
//                gl2.glLoadIdentity();
//
//                gl2.glViewport( 0, 0, width, height );
//            }
//
//            @Override
//            public void init( GLAutoDrawable glAutoDrawable ) {
//                GL2 gl2 = glAutoDrawable.getGL().getGL2();
//
//                String vertexShaderCode = "in vec3 inColor;\nin vec3 inPosition;\nout vec3 color;\nvoid main()\n{\ngl_Position = vec4(inPosition, 1.0);\ncolor = inColor;\n}\n";
//                String fragmentShaderCode = "in vec3 color;\nout vec4 outColor;\nvoid main()\n{\noutColor = vec4(color.x, color.y, color.z, 1.0);\n}\n";
//
//                //File vertexShader = new File("shaders/default.vs");
//                //File fragmentShader = new File("shaders/default.fs");
//
//
//
//                shaderProgram = new ShaderProgram();
//                if (!shaderProgram.init(gl2, vertexShader, fragmentShader)) {
//                    throw new IllegalStateException("Unable to initiate the shaders!");
//                }
//
//                vertexBuffer = Buffers.newDirectFloatBuffer(cube.getVertices().length);
//                indexBuffer = Buffers.newDirectIntBuffer(cube.getIndices().length);
//                colorBuffer = Buffers.newDirectFloatBuffer(cube.getColors().length);
//
//                vertexBuffer.put(cube.getVertices());
//                indexBuffer.put(cube.getIndices());
//                colorBuffer.put(cube.getColors());
//
//                gl2.glEnable(GL2.GL_DEPTH_TEST);
//                gl2.glDepthMask(true);
//            }
//
//            @Override
//            public void dispose( GLAutoDrawable glAutoDrawable ) {
//                GL2 gl2 = glAutoDrawable.getGL().getGL2();
//                //shaderProgram.dispose(gl2);
//                gl2.glDetachShader(programId, vertexShaderId);
//                gl2.glDetachShader(programId, fragmentShaderId);
//                gl2.glDeleteProgram(programId);
//            }
//
//            @Override
//            public void display( GLAutoDrawable glAutoDrawable ) {
//                //OneTriangle.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight() );
//
//                GL2 gl2 = glAutoDrawable.getGL().getGL2();
//
//                gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
//
//                gl2.glUseProgram(shaderProgram.getProgramId());
//
//                gl2.glEnableVertexAttribArray(shaderProgram
//                        .getShaderAttributeLocation(EShaderAttribute.POSITION));
//                gl2.glEnableVertexAttribArray(shaderProgram
//                        .getShaderAttributeLocation(EShaderAttribute.COLOR));
//
//                gl2.glVertexAttribPointer(shaderProgram
//                                .getShaderAttributeLocation(EShaderAttribute.POSITION), 3,
//                        GL2.GL_FLOAT, false, 0, vertexBuffer.rewind());
//
//                gl2.glVertexAttribPointer(shaderProgram
//                                .getShaderAttributeLocation(EShaderAttribute.COLOR), 3,
//                        GL2.GL_FLOAT, false, 0, colorBuffer.rewind());
//
//                gl2.glDrawElements(GL2.GL_TRIANGLES, cube.getIndices().length,
//                        GL2.GL_UNSIGNED_INT, indexBuffer.rewind());
//
//                gl2.glDisableVertexAttribArray(shaderProgram
//                        .getShaderAttributeLocation(EShaderAttribute.POSITION));
//                gl2.glDisableVertexAttribArray(shaderProgram
//                        .getShaderAttributeLocation(EShaderAttribute.COLOR));
//
//                gl2.glUseProgram(0);
//            }
//        });

        myToolWindowContent.add( gljpanel, BorderLayout.CENTER );

        //JButton northButton = new JButton("North");
        //myToolWindowContent.add(northButton, BorderLayout.NORTH);
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}
