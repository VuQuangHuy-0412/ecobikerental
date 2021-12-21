package EcoBikeRental.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcoBikeRental.Dao.DockDao;
import EcoBikeRental.Entity.Dock;

@Service
public class DockService {
	@Autowired
	DockDao dockDao;
	
	public List<String> getListDockProvince() {
		try {
			List<String> listProvince = new ArrayList<String>();
			List<Dock> listDockProvince = dockDao.getListDockProvince();
			for (Dock dock : listDockProvince) {
				listProvince.add(dock.getProvince());
			}
			return listProvince;
		} catch(Exception e) {
			return null;
		}
	}
	
	public List<Dock> getAllDock() {
		try {
			List<Dock> listDock = dockDao.getAllDock();
			return listDock;
		} catch(Exception e) {
			return null;
		}
	}
	
	public List<Dock> getListDockByKeyword(String keyword) {
		try {
			List<Dock> listDock = dockDao.getListDockByKeyword(keyword);
			return listDock;
		} catch(Exception e) {
			return null;
		}
	}
	
	public Dock getDockByDockId(Integer dockId) {
		try {
			Dock dock = dockDao.getDockByDockId(dockId);
			return dock;
		} catch (Exception e) {
			return null;
		}
	}
}
