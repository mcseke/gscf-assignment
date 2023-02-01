package com.gscf.assignment;

import com.gscf.assignment.components.WallpaperCalculator;
import com.gscf.assignment.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class AssignmentApplicationTests {
	@Autowired
	private WallpaperCalculator wallpaperCalculator;

	@Test
	void testWallpaperNeeded() {
		Long expectedWallpaperNeeded = 871L;

		Assertions.assertEquals(expectedWallpaperNeeded, wallpaperCalculator.calculateWallpaperNeeded());
	}

	@Test
	void testListCubicShapedRoomsOrderedByWallpaperNeededDesc() {
		List<Room> expectedCubicShapedRoomsOrderedByWallpaperNeededDesc = Arrays.asList(new Room(2,2,2),new Room(1,1,1), new Room(1,1,1));

		List<Room> cubicShapedRoomsOrderedByWallpaperNeededDesc = wallpaperCalculator.listCubicShapedRoomsOrderedByWallpaperNeededDesc();
		Assertions.assertEquals(expectedCubicShapedRoomsOrderedByWallpaperNeededDesc.size(), cubicShapedRoomsOrderedByWallpaperNeededDesc.size());
		expectedCubicShapedRoomsOrderedByWallpaperNeededDesc.forEach(room ->
			Assertions.assertTrue(cubicShapedRoomsOrderedByWallpaperNeededDesc.contains(room))
		);
	}

	@Test
	void testListRoomsAppearingMoreThenOnce() {
		List<Room> expectedRoomsAppearingMoreThenOnce = Arrays.asList(new Room(1,1,1), new Room(3,8,9));

		List<Room> listRoomsAppearingMoreThenOnce = wallpaperCalculator.listRoomsAppearingMoreThenOnce();
		Assertions.assertEquals(2, listRoomsAppearingMoreThenOnce.size());
		expectedRoomsAppearingMoreThenOnce.forEach(room ->
			Assertions.assertTrue(listRoomsAppearingMoreThenOnce.contains(room))
		);
	}
}

