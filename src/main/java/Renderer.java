import java.io.File;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

/**
 * Performs the rendering.
 *
 * @author serhiy
 *
 * https://github.com/serhiy/jogl-lesson-2
 */
public class Renderer implements GLEventListener {

    private Cube cube = new Cube();
    private FloatBuffer vertexBuffer;
    private IntBuffer indexBuffer;
    private FloatBuffer colorBuffer;
    private ShaderProgram shaderProgram;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl2 = glAutoDrawable.getGL().getGL2();

//        File vertexShader = new File("shaders/default.vs");
//        File fragmentShader = new File("shaders/default.fs");

        String vertexShaderCode = "in vec3 inColor;\nin vec3 inPosition;\nout vec3 color;\nvoid main()\n{\ngl_Position = vec4(inPosition, 1.0);\ncolor = inColor;\n}\n";
        String fragmentShaderCode = "in vec3 color;\nout vec4 outColor;\nvoid main()\n{\noutColor = vec4(color.x, color.y, color.z, 1.0);\n}\n";


        shaderProgram = new ShaderProgram();
        if (!shaderProgram.init(gl2, vertexShaderCode, fragmentShaderCode)) {
            throw new IllegalStateException("Unable to initiate the shaders!");
        }

        vertexBuffer = Buffers.newDirectFloatBuffer(cube.getVertices().length);
        indexBuffer = Buffers.newDirectIntBuffer(cube.getIndices().length);
        colorBuffer = Buffers.newDirectFloatBuffer(cube.getColors().length);

        vertexBuffer.put(cube.getVertices());
        indexBuffer.put(cube.getIndices());
        colorBuffer.put(cube.getColors());

        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glDepthMask(true);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
        GL2 gl2 = glAutoDrawable.getGL().getGL2();
        shaderProgram.dispose(gl2);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl2 = glAutoDrawable.getGL().getGL2();

        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl2.glUseProgram(shaderProgram.getProgramId());

        gl2.glEnableVertexAttribArray(shaderProgram
                .getShaderAttributeLocation(EShaderAttribute.POSITION));
        gl2.glEnableVertexAttribArray(shaderProgram
                .getShaderAttributeLocation(EShaderAttribute.COLOR));

        gl2.glVertexAttribPointer(shaderProgram
                        .getShaderAttributeLocation(EShaderAttribute.POSITION), 3,
                GL2.GL_FLOAT, false, 0, vertexBuffer.rewind());

        gl2.glVertexAttribPointer(shaderProgram
                        .getShaderAttributeLocation(EShaderAttribute.COLOR), 3,
                GL2.GL_FLOAT, false, 0, colorBuffer.rewind());

        gl2.glDrawElements(GL2.GL_TRIANGLES, cube.getIndices().length,
                GL2.GL_UNSIGNED_INT, indexBuffer.rewind());

        gl2.glDisableVertexAttribArray(shaderProgram
                .getShaderAttributeLocation(EShaderAttribute.POSITION));
        gl2.glDisableVertexAttribArray(shaderProgram
                .getShaderAttributeLocation(EShaderAttribute.COLOR));

        gl2.glUseProgram(0);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width,
                        int height) {
        /* no action to be taken on reshape */
    }
}