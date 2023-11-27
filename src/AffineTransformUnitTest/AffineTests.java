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
		System.out.println(scaledv3d.getX());
	}

}
