package AffineTransforms;

import ru.vsu.cs.Math.*;

/**
 * Класс для аффинных преобразований модели
 * 
 * */
public class AffineTransform {
	
	public AffineTransform() {
		
	}
	
	/**
	 * Аффинное смещение (Translation - T)
	 * 
	 * @param model Модель
	 * @param x Величина смещения модели вдоль оси x
	 * @param y Величина смещения модели вдоль оси y
	 * @param z Величина смещения модели вдоль оси z
	 * */
	public static void affineT(Model model, float x, float y, float z) {
		
		//Translate vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.translate(vec, x, y, z));
		}
	}
	
	/**
	 * Аффинное масштабирование (Scale - S)
	 * 
	 * @param model Модель
	 * @param x Величина масштабирования вдоль оси x
	 * @param y Величина масштабирования вдоль оси y
	 * @param z Величина масштабирования вдоль оси z
	 * */
	public static void affineS(Model model, float x, float y, float z) {
		
		//Scale vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.scale(vec, x, y, z));
		}
	}
	
	/**
	 * Аффинное вращение (Rotation - R)
	 * 
	 * @param model Модель
	 * @param x Угол поворота вдоль оси x
	 * @param y Угол поворота вдоль оси y
	 * @param z Угол поворота вдоль оси z
	 * */
	public static void affineR(Model model, float x, float y, float z) {
		
		//Rotate vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.rotate(vec, x, y, z));
		}
	}
	
	/**
	 * Вспомогательный метод для чтения координат вершин модели
	 * Может использоваться для отслеживания корректности преобразований
	 * 
	 * @param model Модель
	 * */
	public static void checkVertexList(Model model) {
		for (Vector3f vec : model.vertices) {
			System.out.println(vec.getX() + ";" + vec.getY() + ";" + vec.getZ());
		}
	}
}
