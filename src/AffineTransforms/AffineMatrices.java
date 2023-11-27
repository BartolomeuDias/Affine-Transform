package AffineTransforms;

import ru.vsu.cs.Math.*;
public class AffineMatrices {
	
	private static float[][] m = new float[4][4];
	
	public AffineMatrices() {
		
	}
	
	private static void initScale(float x, float y, float z) {
		m[0][0] = x;	m[0][1] = 0; 	m[0][2] = 0; m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = y; 	m[1][2] = 0; m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0; 	m[2][2] = z; m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0; 	m[3][2] = 0; m[3][3] = 1;
	}
	
	private static void initRotation(float x, float y, float z) {
		float[][] rx = new float[4][4];
		float[][] ry = new float[4][4];
		float[][] rz = new float[4][4];
		
		x =(float) Math.toRadians(x);
		y =(float) Math.toRadians(y);
		z =(float) Math.toRadians(z);
		
		rz[0][0] = (float)Math.cos(z);		rz[0][1] = (float)Math.sin(z);		rz[0][2] = 0;		rz[0][3] = 0;
		rz[1][0] = -(float)Math.sin(z);		rz[1][1] = (float)Math.cos(z);		rz[1][2] = 0;		rz[1][3] = 0;
		rz[2][0] = 0;		rz[2][1] = 0;		rz[2][2] = 1;		rz[2][3] = 0;
		rz[3][0] = 0;		rz[3][1] = 0;		rz[3][2] = 0;		rz[3][3] = 1;
		
		rx[0][0] = 1;		rx[0][1] = 0;		rx[0][2] = 0;		rx[0][3] = 0;
		rx[1][0] = 0;		rx[1][1] = (float)Math.cos(x);		rx[1][2] = (float)Math.sin(x);		rx[1][3] = 0;
		rx[2][0] = 0;		rx[2][1] = -(float)Math.sin(x);		rx[2][2] = (float)Math.cos(x);		rx[2][3] = 0;
		rx[3][0] = 0;		rx[3][1] = 0;		rx[3][2] = 0;		rx[3][3] = 1;
		
		ry[0][0] = (float)Math.cos(y);		ry[0][1] = 0;		ry[0][2] = (float)Math.sin(y);		ry[0][3] = 0;
		ry[1][0] = 0;		ry[1][1] = 1;		ry[1][2] = 0;		ry[1][3] = 0;
		ry[2][0] = -(float)Math.sin(y);		ry[2][1] = 0;		ry[2][2] = (float)Math.cos(y);		ry[2][3] = 0;
		ry[3][0] = 0;		ry[3][1] = 0;		ry[3][2] = 0;		ry[3][3] = 1;
		
		m = matrixMul(rz,matrixMul(ry, rx));
	}
	
	private static void initTranslation(float x, float y, float z) {
		m[0][0] = 1;	m[0][1] = 0; 	m[0][2] = 0; m[0][3] = x;
		m[1][0] = 0;	m[1][1] = 1; 	m[1][2] = 0; m[1][3] = y;
		m[2][0] = 0;	m[2][1] = 0; 	m[2][2] = 1; m[2][3] = z;
		m[3][0] = 0;	m[3][1] = 0; 	m[3][2] = 0; m[3][3] = 1;
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
	
	private static float[][] matrixMul(float[][] firstM, float[][] secondM){
		float[][] result = new float[firstM.length][secondM[0].length];
		for(int i = 0; i < firstM. length; i++) {
			for(int j = 0; j < secondM[0].length; j++) {
				result[i][j] = 0;
				for(int k = 0; k < firstM[0].length; k++) {
					result[i][j] += firstM[i][k] * secondM[k][j];
				}
			}
		}
		return result;
	}
	
	private static Vector4f transform(Vector4f r)
	{
		return new Vector4f(m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3] * r.getW(),
                			m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3] * r.getW(),
                			m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3] * r.getW(),
                			m[3][0] * r.getX() + m[3][1] * r.getY() + m[3][2] * r.getZ() + m[3][3] * r.getW());
	}
}
