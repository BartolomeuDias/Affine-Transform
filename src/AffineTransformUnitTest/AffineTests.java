package AffineTransformUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import AffineTransforms.*;
import ru.vsu.cs.Math.*;
class AffineTests {
	
	@Test
	void scale1() {
		Vector3f v3f = new Vector3f(1, 1, 1);
		Vector3f scaledv3d = AffineMatrices.scaleVN(v3f, 3, 3, 3);
		Assertions.assertEquals(3, scaledv3d.getX());
		Assertions.assertEquals(3, scaledv3d.getY());
		Assertions.assertEquals(3, scaledv3d.getZ());
	}
	
	@Test
	void scale2() {
		Vector3f v3f = new Vector3f(5, 7, 9);
		Vector3f scaledv3d = AffineMatrices.scaleVN(v3f, 0, 0, 0);
		Assertions.assertEquals(5, scaledv3d.getX());
		Assertions.assertEquals(7, scaledv3d.getY());
		Assertions.assertEquals(9, scaledv3d.getZ());
	}
	
	@Test
	void scale3() {
		Vector3f v3f = new Vector3f(5, 8, 9);
		Vector3f scaledv3d = AffineMatrices.scaleVN(v3f, -2, -4, -3);
		Assertions.assertEquals(2.5, scaledv3d.getX());
		Assertions.assertEquals(2, scaledv3d.getY());
		Assertions.assertEquals(3, scaledv3d.getZ());
	}
	
	@Test
	void translate1() {
		Vector3f v3f = new Vector3f(1, 1, 1);
		Vector3f translatedv3f = AffineMatrices.translateVN(v3f, 15, 8, -3);
		Assertions.assertEquals(16, translatedv3f.getX());
		Assertions.assertEquals(9, translatedv3f.getY());
		Assertions.assertEquals(-2, translatedv3f.getZ());
	}
	
	@Test
	void translate2() {
		Vector3f v3f = new Vector3f(1, 1, 1);
		Vector3f translatedv3f = AffineMatrices.translateVN(v3f, 0, 10, -1);
		Assertions.assertEquals(1, translatedv3f.getX());
		Assertions.assertEquals(11, translatedv3f.getY());
		Assertions.assertEquals(0, translatedv3f.getZ());
	}
	
	@Test
	void rotateZ() {
		double eps = 0.00001;
		Vector3f v3f = new Vector3f(1, 1, 1);
		Vector3f rotatev3f = AffineMatrices.rotateVN(v3f, 0, 0, 45);
		Assertions.assertTrue(Math.sqrt(2) - rotatev3f.getX() < eps);
		Assertions.assertEquals(0, rotatev3f.getY());
		Assertions.assertEquals(1, rotatev3f.getZ());
	}
	
	@Test
	void rotateY() {
		double eps = 0.00001;
		Vector3f v3f = new Vector3f(1, 1, 1);
		Vector3f rotatev3f = AffineMatrices.rotateVN(v3f, 0, 45, 0);
		Assertions.assertTrue(Math.sqrt(2) - rotatev3f.getX() < eps);
		Assertions.assertEquals(1, rotatev3f.getY());
		Assertions.assertEquals(0, rotatev3f.getZ());
	}
	
	@Test
	void rotateX() {
		double eps = 0.00001;
		Vector3f v3f = new Vector3f(1, 1, 1);
		Vector3f rotatev3f = AffineMatrices.rotateVN(v3f, 45, 0, 0);
		Assertions.assertEquals(1, rotatev3f.getX());
		Assertions.assertTrue(Math.sqrt(2) - rotatev3f.getY() < eps);
		Assertions.assertEquals(0, rotatev3f.getZ());
	}
	
	//Texture vertexes
	@Test
	void scale2f() {
		Vector2f v2f = new Vector2f(1, 1);
		Vector2f scaledv2d = AffineMatrices.scaleT(v2f, 3, 3, 3);
		
		System.out.println(scaledv2d.getX());
		System.out.println(scaledv2d.getY());
		Assertions.assertEquals(3, scaledv2d.getX());
		Assertions.assertEquals(3, scaledv2d.getY());
	}
	
	
}
