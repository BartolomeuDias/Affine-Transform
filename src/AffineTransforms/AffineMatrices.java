package AffineTransforms;

import ru.vsu.cs.Math.*;
public class AffineMatrices {
	
	private static Matrix4f mat = new Matrix4f(new float[4][4]);
	
	private static void initScale(float x, float y, float z) {
		if(x == 0) 
			x = 1;
		if(y == 0) 
			y = 1;
		if(z == 0) 
			z = 1;
		if(x < 0) 
			x = Math.abs(1 / x);
		if(y < 0)
			y = Math.abs(1 / y);
		if(z < 0)
			z = Math.abs(1 / z);
		
		mat.getMatrix()[0][0] = x;	mat.getMatrix()[0][1] = 0; 	mat.getMatrix()[0][2] = 0; mat.getMatrix()[0][3] = 0;
		mat.getMatrix()[1][0] = 0;	mat.getMatrix()[1][1] = y; 	mat.getMatrix()[1][2] = 0; mat.getMatrix()[1][3] = 0;
		mat.getMatrix()[2][0] = 0;	mat.getMatrix()[2][1] = 0; 	mat.getMatrix()[2][2] = z; mat.getMatrix()[2][3] = 0;
		mat.getMatrix()[3][0] = 0;	mat.getMatrix()[3][1] = 0; 	mat.getMatrix()[3][2] = 0; mat.getMatrix()[3][3] = 1;
	}
	
	private static void initRotation(float x, float y, float z) {
		Matrix4f rx = new Matrix4f(new float[4][4]);
		Matrix4f ry = new Matrix4f(new float[4][4]);
		Matrix4f rz = new Matrix4f(new float[4][4]);
		
		x =(float) Math.toRadians(x);
		y =(float) Math.toRadians(y);
		z =(float) Math.toRadians(z);
		
		rz.getMatrix()[0][0] = (float)Math.cos(z);		rz.getMatrix()[0][1] = (float)Math.sin(z);		rz.getMatrix()[0][2] = 0;						rz.getMatrix()[0][3] = 0;
		rz.getMatrix()[1][0] = -(float)Math.sin(z);		rz.getMatrix()[1][1] = (float)Math.cos(z);		rz.getMatrix()[1][2] = 0;						rz.getMatrix()[1][3] = 0;
		rz.getMatrix()[2][0] = 0;						rz.getMatrix()[2][1] = 0;						rz.getMatrix()[2][2] = 1;						rz.getMatrix()[2][3] = 0;
		rz.getMatrix()[3][0] = 0;						rz.getMatrix()[3][1] = 0;						rz.getMatrix()[3][2] = 0;						rz.getMatrix()[3][3] = 1;
		
		rx.getMatrix()[0][0] = 1;						rx.getMatrix()[0][1] = 0;						rx.getMatrix()[0][2] = 0;						rx.getMatrix()[0][3] = 0;
		rx.getMatrix()[1][0] = 0;						rx.getMatrix()[1][1] = (float)Math.cos(x);		rx.getMatrix()[1][2] = (float)Math.sin(x);		rx.getMatrix()[1][3] = 0;
		rx.getMatrix()[2][0] = 0;						rx.getMatrix()[2][1] = -(float)Math.sin(x);		rx.getMatrix()[2][2] = (float)Math.cos(x);		rx.getMatrix()[2][3] = 0;
		rx.getMatrix()[3][0] = 0;						rx.getMatrix()[3][1] = 0;						rx.getMatrix()[3][2] = 0;						rx.getMatrix()[3][3] = 1;
		
		ry.getMatrix()[0][0] = (float)Math.cos(y);		ry.getMatrix()[0][1] = 0;						ry.getMatrix()[0][2] = (float)Math.sin(y);		ry.getMatrix()[0][3] = 0;
		ry.getMatrix()[1][0] = 0;						ry.getMatrix()[1][1] = 1;						ry.getMatrix()[1][2] = 0;						ry.getMatrix()[1][3] = 0;
		ry.getMatrix()[2][0] = -(float)Math.sin(y);		ry.getMatrix()[2][1] = 0;						ry.getMatrix()[2][2] = (float)Math.cos(y);		ry.getMatrix()[2][3] = 0;
		ry.getMatrix()[3][0] = 0;						ry.getMatrix()[3][1] = 0;						ry.getMatrix()[3][2] = 0;						ry.getMatrix()[3][3] = 1;
		
		mat = rz.multiply(ry.multiply(rx));
	}
	
	private static void initTranslation(float x, float y, float z) {
		mat.getMatrix()[0][0] = 1;	mat.getMatrix()[0][1] = 0; 	mat.getMatrix()[0][2] = 0; mat.getMatrix()[0][3] = x;
		mat.getMatrix()[1][0] = 0;	mat.getMatrix()[1][1] = 1; 	mat.getMatrix()[1][2] = 0; mat.getMatrix()[1][3] = y;
		mat.getMatrix()[2][0] = 0;	mat.getMatrix()[2][1] = 0; 	mat.getMatrix()[2][2] = 1; mat.getMatrix()[2][3] = z;
		mat.getMatrix()[3][0] = 0;	mat.getMatrix()[3][1] = 0; 	mat.getMatrix()[3][2] = 0; mat.getMatrix()[3][3] = 1;
	}
	
	public static Vector3f translateVN(Vector3f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), vector.getZ(), 1);
		initTranslation(x, y, z);
		current = transform(current);
		return new Vector3f(current.getX(), current.getY(), current.getZ());
	}
	
	public static Vector3f scaleVN(Vector3f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), vector.getZ(), 1);
		initScale(x, y, z);
		current = transform(current);
		return new Vector3f(current.getX(), current.getY(), current.getZ());
	}
	
	public static Vector3f rotateVN(Vector3f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), vector.getZ(), 1);
		initRotation(x, y, z);
		current = transform(current);
		return new Vector3f(current.getX(), current.getY(), current.getZ());
	}
	
	public static Vector2f translateT(Vector2f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), 0, 1);
		initTranslation(x, y, z);
		current = transform(current);
		return new Vector2f(current.getX(), current.getY());
	}
	
	public static Vector2f scaleT(Vector2f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), 0, 1);
		initScale(x, y, z);
		current = transform(current);
		return new Vector2f(current.getX(), current.getY());
	}
	
	public static Vector2f rotateT(Vector2f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), 0, 1);
		initRotation(x, y, z);
		current = transform(current);
		return new Vector2f(current.getX(), current.getY());
	}
	
	private static Vector4f transform(Vector4f r)
	{
		return new Vector4f(mat.getMatrix()[0][0] * r.getX() + mat.getMatrix()[0][1] * r.getY() + mat.getMatrix()[0][2] * r.getZ() + mat.getMatrix()[0][3] * r.getW(),
							mat.getMatrix()[1][0] * r.getX() + mat.getMatrix()[1][1] * r.getY() + mat.getMatrix()[1][2] * r.getZ() + mat.getMatrix()[1][3] * r.getW(),
                			mat.getMatrix()[2][0] * r.getX() + mat.getMatrix()[2][1] * r.getY() + mat.getMatrix()[2][2] * r.getZ() + mat.getMatrix()[2][3] * r.getW(),
                			mat.getMatrix()[3][0] * r.getX() + mat.getMatrix()[3][1] * r.getY() + mat.getMatrix()[3][2] * r.getZ() + mat.getMatrix()[3][3] * r.getW());
	}
}
