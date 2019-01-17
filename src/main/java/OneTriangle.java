import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.glsl.ShaderProgram;

public class OneTriangle {
    protected static void setup( GL2 gl2, int width, int height ) {
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height same as the window
        GLU glu = new GLU();
        glu.gluOrtho2D( 0.0f, width, 0.0f, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );
    }

    protected static void render( GL2 gl2, int width, int height ) {
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );

        String vertexShaderCode = "in vec3 inColor;\nin vec3 inPosition;\nout vec3 color;\nvoid main()\n{\ngl_Position = vec4(inPosition, 1.0);\ncolor = inColor;\n}\n";
        String fragmentShaderCode = "in vec3 color;\nout vec4 outColor;\nvoid main()\n{\noutColor = vec4(color.x, color.y, color.z, 1.0);\n}\n";


        // draw a triangle filling the window
        //gl2.glLoadIdentity();
        //gl2.glBegin( GL.GL_TRIANGLES );
        //gl2.glColor3f( 1, 0, 0 );
        //gl2.glColor3f( red, green, blue );
        //gl2.glVertex2f( 0, 0 );
        //gl2.glColor3f( 0, 1, 0 );
        //gl2.glColor3f( blue, red, green );
        //gl2.glVertex2f( width, 0 );
        //gl2.glColor3f( 0, 0, 1 );
        //gl2.glColor3f( green, blue, red );
        //gl2.glVertex2f( width / 2, height );
        //gl2.glEnd();
        //red += 0.1f; if (red > 1.f) red = 0.f;
        //green += 0.1f; if (green > 1.f) green = 0.f;
        //blue += 0.1f; if (blue > 1.f) blue = 0.f;
    }
    //private static float red = 0.1f;
    //private static float green = 0.0f;
    //private static float blue = 0.0f;

    private static String vertexShaderCode = "in vec3 inColor;\nin vec3 inPosition;\nout vec3 color;\nvoid main()\n{\ngl_Position = vec4(inPosition, 1.0);\ncolor = inColor;\n}\n";
    private static String fragmentShaderCode = "in vec3 color;\nout vec4 outColor;\nvoid main()\n{\noutColor = vec4(color.x, color.y, color.z, 1.0);\n}\n";

}