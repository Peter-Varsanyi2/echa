package bot;

import java.util.Comparator;

import main.Region;

public class RegionComparator implements Comparator<Region>{

	@Override
	public int compare(Region r1, Region r2) {
		return r1.getArmies() < r2.getArmies() ? -1 : r1.getArmies() == r2.getArmies() ? 0 : 1;
	}

}
