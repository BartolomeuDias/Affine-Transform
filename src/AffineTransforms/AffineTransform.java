package AffineTransforms;

import ru.vsu.cs.Math.*;

public class AffineTransform {
	public AffineTransform() {
		
	}
	public static void affineT(Model model, float x, float y, float z) {
		
		//Translate vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.translateVN(vec, x, y, z));
		}
		
		//Translate texture vertexes
		for(int i = 0; i < model.textureVertices.size(); i++) {
			Vector2f vec = model.textureVertices.get(i);
			model.textureVertices.set(i, AffineMatrices.translateT(vec, x, y, z));
		}
		
		//Translate normals
		for(int i = 0; i < model.normals.size(); i++) {
			Vector3f vec = model.normals.get(i);
			model.normals.set(i, AffineMatrices.translateVN(vec, x, y, z));
		}
	}
	
	public static void affineS(Model model, float x, float y, float z) {
		
		//Scale vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.scaleVN(vec, x, y, z));
		}
		
		//Scale texture vertexes
		for(int i = 0; i < model.textureVertices.size(); i++) {
			Vector2f vec = model.textureVertices.get(i);
			model.textureVertices.set(i, AffineMatrices.scaleT(vec, x, y, z));
		}
		
		//Scale normals
		for(int i = 0; i < model.normals.size(); i++) {
			Vector3f vec = model.normals.get(i);
			model.normals.set(i, AffineMatrices.scaleVN(vec, x, y, z));
		}
	}
	
	public static void affineR(Model model, float x, float y, float z) {
		
		//Rotate vertex
		for(int i = 0; i < model.vertices.size(); i++) {
			Vector3f vec = model.vertices.get(i);
			model.vertices.set(i, AffineMatrices.rotateVN(vec, x, y, z));
		}
		
		//Rotate texture vertexes
		for(int i = 0; i < model.textureVertices.size(); i++) {
			Vector2f vec = model.textureVertices.get(i);
			model.textureVertices.set(i, AffineMatrices.rotateT(vec, x, y, z));
		}
		
		//Rotate normals
		for(int i = 0; i < model.normals.size(); i++) {
			Vector3f vec = model.normals.get(i);
			model.normals.set(i, AffineMatrices.rotateVN(vec, x, y, z));
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
