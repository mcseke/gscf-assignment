package com.gscf.assignment;

import com.gscf.assignment.components.WallpaperCalculator;
import com.gscf.assignment.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AssignmentApplicationTests {

	@Autowired
	private WallpaperCalculator wallpaperCalculator;


	@Test
	void testWallpaperNeeded() {
//		2x3x8 + 2x8x9 + 2X9x3 + 3 = 249
//		2x2x3 + 2x3x7 + 2x2x7 + 2 = 84
//		2x3x8 + 2x8x9 + 2x3x9 + 3 = 249
//		2x1x1 + 2x1x1 + 2x1x1 + 1
//		sum: 589

		Assertions.assertEquals(589L, wallpaperCalculator.calculateWallpaperNeeded());
	}

	@Test
	void testListCubicShapedRoomsOrderedByWallpaperNeededDesc() {
		List<Room> cubicShapedRoomsOrderedByWallpaperNeededDesc = wallpaperCalculator.listCubicShapedRoomsOrderedByWallpaperNeededDesc();
		Assertions.assertEquals(1, cubicShapedRoomsOrderedByWallpaperNeededDesc.size());
		Assertions.assertEquals(new Room(1,1,1), cubicShapedRoomsOrderedByWallpaperNeededDesc.get(0));
	}

	@Test
	void testListRoomsAppearingMoreThenOnce() {
		List<Room> listRoomsAppearingMoreThenOnce = wallpaperCalculator.listRoomsAppearingMoreThenOnce();
		Assertions.assertEquals(1, listRoomsAppearingMoreThenOnce.size());
		Assertions.assertEquals(new Room(3,8,9), wallpaperCalculator.listRoomsAppearingMoreThenOnce().get(0));
	}

}

