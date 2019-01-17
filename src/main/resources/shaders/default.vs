//in vec3 inColor;
//in vec3 inPosition;

//out vec3 color;

//void main()
//{
//    gl_Position = vec4(inPosition, 1.0);
//	color = inColor * 0.5;
//}

#version 400

layout (location = 0) in vec3 VertexPosition;
layout (location = 1) in vec3 VertexColor;

out vec3 Color;

void main()
{
	Color = VertexColor;
    gl_Position = vec4(VertexPosition, 1.0);
}