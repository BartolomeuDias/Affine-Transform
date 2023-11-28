package AffineTransforms;

import ru.vsu.cs.Math.*;
/**
 * Вспомогательный класс для инициализации матриц масштабирования (Scale), поворота (Rotate), перемещения (Translate)
 * 
 * */
public class AffineMatrices {
	
	public AffineMatrices() {
		
	}
	
	private static Matrix4f mat = new Matrix4f(new float[4][4]);
	
	
	/**
	 * Инициализация матрицы увеличения (Scale)
	 * 
	 * @param x Величина масштабирования вдоль оси x
	 * @param y Величина масштабирования вдоль оси y
	 * @param z Величина масштабирования вдоль оси z
	 * */
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
	
	/**
	 * Инициализация матрицы поворота (Rotate)
	 * Результирующая матрица представляет собой произведение матриц поворота относительно осей x, y, z соответственно
	 * 
	 * @param x Угол поворота вдоль оси x
	 * @param y Угол поворота вдоль оси y
	 * @param z Угол поворота вдоль оси z
	 * */
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
	
	/**
	 * Инициализация матрицы смещения (Translate)
	 * 
	 * @param x Величина смещения модели вдоль оси x
	 * @param y Величина смещения модели вдоль оси y
	 * @param z Величина смещения модели вдоль оси z
	 * */
	private static void initTranslation(float x, float y, float z) {
		mat.getMatrix()[0][0] = 1;	mat.getMatrix()[0][1] = 0; 	mat.getMatrix()[0][2] = 0; mat.getMatrix()[0][3] = x;
		mat.getMatrix()[1][0] = 0;	mat.getMatrix()[1][1] = 1; 	mat.getMatrix()[1][2] = 0; mat.getMatrix()[1][3] = y;
		mat.getMatrix()[2][0] = 0;	mat.getMatrix()[2][1] = 0; 	mat.getMatrix()[2][2] = 1; mat.getMatrix()[2][3] = z;
		mat.getMatrix()[3][0] = 0;	mat.getMatrix()[3][1] = 0; 	mat.getMatrix()[3][2] = 0; mat.getMatrix()[3][3] = 1;
	}
	
	/**
	 * Смещение вершины
	 * 
	 * @param vector Вершина, которую мы хотим сместить 
	 * @param x Величина смещения модели вдоль оси x
	 * @param y Величина смещения модели вдоль оси y
	 * @param z Величина смещения модели вдоль оси z
	 * @return Смещенная вершина
	 * */
	protected static Vector3f translate(Vector3f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), vector.getZ(), 1);
		initTranslation(x, y, z);
		current = transform(current);
		return new Vector3f(current.getX(), current.getY(), current.getZ());
	}
	
	/**
	 * Масштабирование вершины
	 * 
	 * @param vector Вершина, масштаб которой мы хотим изменить 
	 * @param x Величина масштабирования модели вдоль оси x
	 * @param y Величина масштабирования модели вдоль оси y
	 * @param z Величина масштабирования модели вдоль оси z
	 * @return Вершина с изменённым масштабом
	 * */
	protected static Vector3f scale(Vector3f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), vector.getZ(), 1);
		initScale(x, y, z);
		current = transform(current);
		return new Vector3f(current.getX(), current.getY(), current.getZ());
	}
	
	/**
	 * Поворот вершины
	 * 
	 * @param vector Вершина, которую мы хотим повернуть
	 * @param x Угол поворота модели вдоль оси x
	 * @param y Угол поворота модели вдоль оси y
	 * @param z Угол поворота модели вдоль оси z
	 * @return Вершина, повёрнутая на заданные углы
	 * */
	protected static Vector3f rotate(Vector3f vector, float x, float y, float z) {
		Vector4f current = new Vector4f(vector.getX(), vector.getY(), vector.getZ(), 1);
		initRotation(x, y, z);
		current = transform(current);
		return new Vector3f(current.getX(), current.getY(), current.getZ());
	}
	
	/**
	 * Преобразование вершины
	 * 
	 * @param v Вершина, которую мы хотим повернуть
	 * @return Изменённая вершина
	 * */
	private static Vector4f transform(Vector4f v)
	{
		return mat.multiply(v);
	}
}
