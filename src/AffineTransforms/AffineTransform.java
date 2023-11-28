package AffineTransforms;

import ru.vsu.cs.Math.*;

public class AffineTransform {
	
	public AffineTransform() {
		
	}
	
	public static void affineT(Model model, float x, float y, float z) {
		
		//Translate vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.translate(vec, x, y, z));
		}
	}
	
	public static void affineS(Model model, float x, float y, float z) {
		
		//Scale vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.scale(vec, x, y, z));
		}
	}
	
	public static void affineR(Model model, float x, float y, float z) {
		
		//Rotate vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.rotate(vec, x, y, z));
		}
	}
	
	public static void checkVertexList(Model model) {
		for (Vector3f vec : model.vertices) {
			System.out.println(vec.getX() + ";" + vec.getY() + ";" + vec.getZ());
		}
	}
	
	public static void checkTextureList(Model model) {
		for (Vector2f vec : model.textureVertices) {
			System.out.println(vec.getX() + ";" + vec.getY());
		}
	}
	
	public static void checkNormalList(Model model) {
		for (Vector3f vec : model.normals) {
			System.out.println(vec.getX() + ";" + vec.getY() + ";" + vec.getZ());
		}
	}
}
