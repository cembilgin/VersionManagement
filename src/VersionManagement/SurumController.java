package VersionManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SurumController {

	String contactId = "";
	List<Surum> list = new ArrayList<Surum>();
	List<Surum> search = new ArrayList<Surum>();
	List<Detay> detaylist = new ArrayList<Detay>();

	List<Detay> detaylistekle = new ArrayList<Detay>();
	String detayId = "";
	String deneme = "";
	int count2 = 0;
	boolean tr = false;
	boolean ekle = false;

	int count = 0;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String surumliste(ModelMap model) {

		if (tr) {

			tr = false;
			model.addAttribute("lists", search);
		} else {

			list = getList();
			model.addAttribute("lists", list);
		}

		return "surumliste";
	}

	public List<Surum> getList() {  // this part for retrieving all data from XDSL_SURUM to our list object.
		list.clear();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");
			java.sql.Statement st = con.createStatement();

			String sql = "select * from XDSL_SURUM WHERE ACTIVE=1 order by ID";
			ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

			while (rs.next()) {
				Surum surum = new Surum();

				surum.setId(rs.getString("ID"));
				surum.setSurum(rs.getString("SURUM"));
				surum.setSurumTipi(rs.getString("SURUM_TIPI"));
				surum.setAnalizTarihi(rs.getDate("ANALIZ_DT").toString());
				surum.setGelistirmeTarihi(rs.getDate("GELISTIRME_DT")
						.toString());
				surum.setTesteTeslimTarihi(rs.getDate("TEST_TESLIM_DT")
						.toString());
				surum.setCanliAlimTarihi(rs.getDate("CANLI_ALIM_DT").toString());
				surum.setDurum(rs.getString("DURUM"));

				list.add(surum);

			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return list;

	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET) //This is for clearing models in add.jsp and 
																//retrieving data from XDSL_SURUM_TIPI and XDSL_SURUM_DURUM to combo boxes
	
	public String addSunum( Surum surum,
			ModelMap model) {
		model.addAttribute("id", "");
		model.addAttribute("surum", "");
		model.addAttribute("surumTipi", "");
		model.addAttribute("analizTarihi", "");
		model.addAttribute("gelistirmeTarihi", "");
		model.addAttribute("testeTeslimTarihi", "");
		model.addAttribute("canliAlimTarihi", "");
		model.addAttribute("durum", "");
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");
			java.sql.Statement st = con.createStatement();

			String sql = "SELECT ACIKLAMA FROM XDSL_SURUM_TIPI";
			ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

			while (rs.next()) {
				l1.add(rs.getString(1));
			}
			String sql2 = "SELECT ACIKLAMA FROM XDSL_SURUM_DURUM";
			ResultSet rs2 = ((java.sql.Statement) st).executeQuery(sql2);

			while (rs2.next()) {
				l2.add(rs2.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("surumlist", l1);
		model.addAttribute("surumlist2", l2);
		return "add";
	}
	@RequestMapping(value = "/addSurum", method = RequestMethod.GET)  //getting data from add.jsp to controller. 
	public String saveSunum( Surum surum,	// after getting data, It will insert into XDSL_SURUM
			ModelMap model) {

		model.addAttribute("surum", surum.getSurum());
		model.addAttribute("surumTipi", surum.getSurumTipi());
		model.addAttribute("analizTarihi", surum.getAnalizTarihi());
		model.addAttribute("gelistirmeTarihi", surum.getGelistirmeTarihi());
		model.addAttribute("testeTeslimTarihi", surum.getTesteTeslimTarihi());
		model.addAttribute("canliAlimTarihi", surum.getCanliAlimTarihi());
		model.addAttribute("durum", surum.getDurum());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");
			
			
			
			
			String sql = "INSERT INTO XDSL_SURUM (SURUM,SURUM_TIPI,ANALIZ_DT,GELISTIRME_DT,TEST_TESLIM_DT,CANLI_ALIM_DT,DURUM, ACTIVE )"
					+ " VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, surum.getSurum());
			ps.setString(2, surum.getSurumTipi());
			ps.setString(3, surum.getAnalizTarihi());
			ps.setString(4, surum.getGelistirmeTarihi());
			ps.setString(5, surum.getTesteTeslimTarihi());
			ps.setString(6, surum.getCanliAlimTarihi());
			ps.setString(7, surum.getDurum());
			ps.setString(8, "1");
			
			 ps.executeUpdate();
			
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		surumliste(model);

		return "surumliste";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String guncellemetod(HttpServletRequest request, ModelMap model) { //getting data from surumliste.jsp(main page) to update.jsp
		Surum surum = null;													//filling the combo boxes from database
		contactId = request.getParameter("id");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(contactId)) {
				surum = list.get(i);
			}
		}
		model.addAttribute("id", surum.getId());
		model.addAttribute("surum", surum.getSurum());
		model.addAttribute("surumTipi", surum.getSurumTipi());
		model.addAttribute("analizTarihi", surum.getAnalizTarihi());
		model.addAttribute("gelistirmeTarihi", surum.getGelistirmeTarihi());
		model.addAttribute("testeTeslimTarihi", surum.getTesteTeslimTarihi());
		model.addAttribute("canliAlimTarihi", surum.getCanliAlimTarihi());
		model.addAttribute("durum", surum.getDurum());
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");
			java.sql.Statement st = con.createStatement();

			String sql = "SELECT ACIKLAMA FROM XDSL_SURUM_TIPI";
			ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

			while (rs.next()) {
				if (rs.getString(1).equals(surum.getSurumTipi())) {
					continue;
				}
				l1.add(rs.getString(1));
			}
			String sql2 = "SELECT ACIKLAMA FROM XDSL_SURUM_DURUM";
			ResultSet rs2 = ((java.sql.Statement) st).executeQuery(sql2);

			while (rs2.next()) {
				if (rs2.getString(1).equals(surum.getDurum())) {
					continue;
				}
				l2.add(rs2.getString(1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("surumlist", l1);
		model.addAttribute("surumlist2", l2);
		return "update";
	}

	@RequestMapping(value = "/updateSurum", method = RequestMethod.GET) //getting data from update.jsp to Controller
	public String saveGüncel(HttpServletRequest request, Surum surum,	   // saving them to our database
			ModelMap model) {

		model.addAttribute("lists", list);
		model.addAttribute("id", surum.getId());
		model.addAttribute("surum", surum.getSurum());
		model.addAttribute("surumTipi", surum.getSurumTipi());
		model.addAttribute("analizTarihi", surum.getAnalizTarihi());
		model.addAttribute("gelistirmeTarihi", surum.getGelistirmeTarihi());
		model.addAttribute("testeTeslimTarihi", surum.getTesteTeslimTarihi());
		model.addAttribute("canliAlimTarihi", surum.getCanliAlimTarihi());
		model.addAttribute("durum", surum.getDurum());

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");
			String sql = "UPDATE XDSL_SURUM SET SURUM = ?, SURUM_TIPI = ?, ANALIZ_DT = ?, GELISTIRME_DT = ?, TEST_TESLIM_DT = ?, CANLI_ALIM_DT = ?, DURUM = ? WHERE ID= ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, surum.getSurum());
			ps.setString(2, surum.getSurumTipi());
			ps.setString(3, surum.getAnalizTarihi());
			ps.setString(4, surum.getGelistirmeTarihi());
			ps.setString(5, surum.getTesteTeslimTarihi());
			ps.setString(6, surum.getCanliAlimTarihi());
			ps.setString(7, surum.getDurum());
			ps.setString(8, contactId);

			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		surumliste(model);

		return "surumliste";
	}

	

	@RequestMapping(value = "/searchSurum", method = RequestMethod.GET)
	public String surumAra(HttpServletRequest request, ModelMap model) { //Making search in our database
																		
		search.clear();
		String surum2 = request.getParameter("surum");
		String canliAlim1 = request.getParameter("canliAlimTarihi1");
		String canliAlim2 = request.getParameter("canliAlimTarihi2");

		model.addAttribute("lists", search);
		tr = true;
		if ((surum2.isEmpty()) && (canliAlim1.isEmpty())
				&& (canliAlim2.isEmpty())) {
			model.addAttribute("lists", list);
			

			tr = false;
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/etiya", "root", "");
				java.sql.Statement st = con.createStatement();
				String sql = "";
				if (surum2.isEmpty()) {

					if (canliAlim2.isEmpty()) {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND CANLI_ALIM_DT >= '"
								+ canliAlim1 + "'";
					} else if (canliAlim1.isEmpty()) {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND CANLI_ALIM_DT <= '"
								+ canliAlim2 + "'";
					} else {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND CANLI_ALIM_DT BETWEEN '"
								+ canliAlim1 + "'  AND '" + canliAlim2 + "'";
					}
				}
				if (canliAlim2.isEmpty()) {
					if (surum2.isEmpty()) {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND CANLI_ALIM_DT >= '"
								+ canliAlim1 + "'";
					} else if (canliAlim1.isEmpty()) {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND "
								+ "SURUM ='" + surum2 + "'";
					} else {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND "
								+ "SURUM ='" + surum2 + "'"
								+ "AND CANLI_ALIM_DT >= '" + canliAlim1 + "'";
					}
				}
				if (canliAlim1.isEmpty()) {
					if (surum2.isEmpty()) {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND CANLI_ALIM_DT <= '"
								+ canliAlim2 + "'";
					} else if (canliAlim2.isEmpty()) {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND "
								+ "SURUM ='" + surum2 + "'";
					} else {
						sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND "
								+ "SURUM ='" + surum2 + "'"
								+ "AND CANLI_ALIM_DT <= '" + canliAlim2 + "'";
					}
				}
				if (!(canliAlim1.isEmpty()) && !(surum2.isEmpty())
						&& !(canliAlim2.isEmpty())) {
					sql = "select * from XDSL_SURUM WHERE ACTIVE=1 AND "
							+ "SURUM ='" + surum2 + "'"
							+ "AND CANLI_ALIM_DT BETWEEN '" + canliAlim1
							+ "'  AND '" + canliAlim2 + "',";
				}
			
				ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

				while (rs.next()) {
					Surum surum = new Surum();

					surum.setId(rs.getString("ID"));
					surum.setSurum(rs.getString("SURUM"));
					surum.setSurumTipi(rs.getString("SURUM_TIPI"));
					surum.setAnalizTarihi(rs.getDate("ANALIZ_DT").toString());
					surum.setGelistirmeTarihi(rs.getDate("GELISTIRME_DT")
							.toString());
					surum.setTesteTeslimTarihi(rs.getDate("TEST_TESLIM_DT")
							.toString());
					surum.setCanliAlimTarihi(rs.getDate("CANLI_ALIM_DT")
							.toString());
					surum.setDurum(rs.getString("DURUM"));

					search.add(surum);

				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

			model.addAttribute("lists", search);
			tr = true;

		}

		surumliste(model);

		return "surumliste";
	}

	@RequestMapping(value = "/deleteSurum", method = RequestMethod.GET)
	public String deleteContact(HttpServletRequest request, Surum surum, // delete from XDSL_SURUM
			ModelMap model) {											// It just update ACTIVE attribute from "1" to "0"
		String contactId =request.getParameter("id");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");
			
			String sql = "UPDATE XDSL_SURUM SET ACTIVE=?  WHERE ID= ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "0");
			ps.setString(2,contactId);
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
		surumliste(model);
		return "surumliste";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detayliste(HttpServletRequest request, ModelMap model) { // This is detail part

		if (ekle) {
System.out.println("detayId is "+detayId);
			ekle = false;

		} else {
			detayId = request.getParameter("ID");

		}

		detaylist.clear();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");
			java.sql.Statement st = con.createStatement();
			String sql = "select * from XDSL_SURUM_DETAY WHERE ACTIVE=1 AND SURUM_ID="
					+ detayId + " order by ID";
			
			ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);
System.out.println(sql);
System.out.println("detay");
			while (rs.next()) {
				Detay detay = new Detay();
				int l = rs.getInt("ID");
				String k = l + "";
				detay.setId(k);
				detay.setJiraLink(rs.getString("JIRA_LINK"));
				detay.setBaslik(rs.getString("BASLIK"));
				detay.setAciklama(rs.getString("ACIKLAMA"));
				detay.setMaliyet(rs.getString("MALIYET"));
				detay.setTesteTeslimTarihi(rs.getDate("TTARIH").toString());
				detay.setTestKullanici(rs.getString("TKULLANICI"));
				detay.setGelistirmeTarihi(rs.getDate("GTARIH").toString());
				detay.setGuncelKullanici(rs.getString("GKULLANICI"));
				detay.setSurumId(rs.getString("SURUM_ID"));
				detay.setDefectId(rs.getString("DEFECT_ID"));
				
				detay.setCount(count);
				detaylist.add(detay);

			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		if (!(detaylistekle.isEmpty())) {
			for (int i = 0; i < detaylistekle.size(); i++) {
				detaylist.add(detaylistekle.get(i));
			}
		}																		// cleaning all data in detail.jsp
		model.addAttribute("lists", detaylist);
		model.addAttribute("kl", detayId);
		model.addAttribute("jiraLink", "");
		model.addAttribute("baslik", "");
		model.addAttribute("aciklama", "");
		model.addAttribute("maliyet", "");
		model.addAttribute("testeTeslimTarihi", "");
		model.addAttribute("testKullanici", "");
		model.addAttribute("gelistirmeTarihi", "");
		model.addAttribute("guncelKullanici", "");
		model.addAttribute("defectId", "");
		model.addAttribute("NumberofAddition",detaylistekle.size());
		return "detail";
	}

	@RequestMapping(value = "/detaykaydet", method = RequestMethod.GET)     //After clicking save button. It comes to this method
	public String detaykaydet(HttpServletRequest request, Detay detay,
			ModelMap model) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/etiya", "root", "");

			for (int i = 0; i < detaylistekle.size(); i++) {
				String sql = "INSERT INTO XDSL_SURUM_DETAY (SURUM_ID,DEFECT_ID,JIRA_LINK,BASLIK,ACIKLAMA,MALIYET,TTARIH,TKULLANICI,GTARIH, GKULLANICI, ACTIVE )"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
						
				PreparedStatement ps =con.prepareStatement(sql);
				ps.setString(1, deneme);
				ps.setString(2, detaylistekle.get(i).getDefectId());
				ps.setString(3, detaylistekle.get(i).getJiraLink());
				ps.setString(4, detaylistekle.get(i).getBaslik());
				ps.setString(5, detaylistekle.get(i).getAciklama());
				ps.setString(6, detaylistekle.get(i).getMaliyet());
				ps.setString(7, detaylistekle.get(i).getTesteTeslimTarihi());
				ps.setString(8, detaylistekle.get(i).getTestKullanici());
				ps.setString(9, detaylistekle.get(i).getGelistirmeTarihi());
				ps.setString(10, detaylistekle.get(i).getGuncelKullanici());
				ps.setString(11, "1");
				ps.executeUpdate();
				

			}

			model.addAttribute("Save", "1");
			con.close();
		} catch (Exception e) {
			model.addAttribute("Save", "0");
			System.out.println(e);
		}
		finally {
			ekle = true;
			detaylist.clear();
			detaylistekle.clear();
			count = 0;
		}
		

detayliste(request, model);

		return "detail";
	}


	// detay.jsp nin eklesi
	@RequestMapping(value = "/detayeklesubmit", method = RequestMethod.GET) // After clicking add button. It comes to this method
	public String detayekle(HttpServletRequest request, Detay detay,
			ModelMap model) {

		detay.setSurumId(detayId);
		deneme = detayId;
		model.addAttribute("surumId", detayId);
		model.addAttribute("defectId", detay.getDefectId());
		model.addAttribute("jiraLink", detay.getJiraLink());
		model.addAttribute("baslik", detay.getBaslik());
		model.addAttribute("aciklama", detay.getAciklama());
		model.addAttribute("maliyet", detay.getMaliyet());
		model.addAttribute("testeTeslimTarihi", detay.getTesteTeslimTarihi());
		model.addAttribute("testKullanici", detay.getTestKullanici());
		model.addAttribute("gelistirmeTarihi", detay.getGelistirmeTarihi());
		model.addAttribute("guncelKullanici", detay.getGuncelKullanici());
		count++;
		detay.setCount(count);

		detaylistekle.add(detay);

		ekle=true;
		detayliste(request, model);
		return "detail";
	}

	
	@RequestMapping(value = "/updateDetailSurum", method = RequestMethod.GET)		// Getting data from detail.jsp to updateDetail.jsp
	public String surumdetayguncelle(HttpServletRequest request, Detay detay,
			ModelMap model) {	

		model.addAttribute("surumId", detay.getSurumId());
		model.addAttribute("defectId", detay.getDefectId());
		model.addAttribute("jiraLink", detay.getJiraLink());
		model.addAttribute("baslik", detay.getBaslik());
		model.addAttribute("aciklama", detay.getAciklama());
		model.addAttribute("maliyet", detay.getMaliyet());
		model.addAttribute("testeTeslimTarihi", detay.getTesteTeslimTarihi());
		model.addAttribute("testKullanici", detay.getTestKullanici());
		model.addAttribute("gelistirmeTarihi", detay.getGelistirmeTarihi());
		model.addAttribute("guncelKullanici", detay.getGuncelKullanici());

		if (count2 > 0) {
			detaylistekle.get(count2 - 1).setAciklama(detay.getAciklama());
			detaylistekle.get(count2 - 1).setBaslik(detay.getBaslik());
			detaylistekle.get(count2 - 1).setDefectId(detay.getDefectId());
			detaylistekle.get(count2 - 1).setJiraLink(detay.getJiraLink());
			detaylistekle.get(count2 - 1).setMaliyet(detay.getMaliyet());
			detaylistekle.get(count2 - 1).setTesteTeslimTarihi(
					detay.getTesteTeslimTarihi());
			detaylistekle.get(count2 - 1).setTestKullanici(
					detay.getTestKullanici());
			detaylistekle.get(count2 - 1).setGelistirmeTarihi(
					detay.getGelistirmeTarihi());
			detaylistekle.get(count2 - 1).setGuncelKullanici(
					detay.getGuncelKullanici());
		} else {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/etiya", "root", "");
				
				String sql = "UPDATE XDSL_SURUM_DETAY SET JIRA_LINK =?, BASLIK =?, ACIKLAMA =?, MALIYET =? , TTARIH =? ,TKULLANICI =?, GTARIH =? ,  GKULLANICI=? ,"
						+ " DEFECT_ID=? WHERE ID=?";

				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, detay.getJiraLink());
				ps.setString(2, detay.getBaslik());
				ps.setString(3, detay.getAciklama());
				ps.setString(4, detay.getMaliyet());
				ps.setString(5, detay.getTesteTeslimTarihi());
				ps.setString(6, detay.getTestKullanici());
				ps.setString(7, detay.getGelistirmeTarihi());
				ps.setString(8, detay.getGuncelKullanici());
				ps.setString(9, detay.getDefectId());
				ps.setString(10, contactId);
				
				
				ps.executeUpdate();

				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		ekle = true;
		detayliste(request, model);
		return "detail";
	}

	@RequestMapping(value = "/updateDetail", method = RequestMethod.GET)
	public String detayguncelle(HttpServletRequest request, ModelMap model) { //Updating details
		Detay detay = null;
		contactId = request.getParameter("ID");
		count2 = Integer.parseInt(request.getParameter("cn"));

		if (contactId.isEmpty()) {
			detay = detaylistekle.get(count2 - 1);
		} else {
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/etiya", "root", "");
				
				String sql = "select * from XDSL_SURUM_DETAY WHERE ACTIVE=? AND ID= ?";
				PreparedStatement ps =con.prepareStatement(sql);
				ps.setString(1, "1");
				ps.setString(2, contactId);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {

					detay = new Detay();
					int l = rs.getInt("ID");
					String k = l + "";
					detay.setId(k);
					detay.setJiraLink(rs.getString("JIRA_LINK"));
					detay.setBaslik(rs.getString("BASLIK"));
					detay.setAciklama(rs.getString("ACIKLAMA"));
					detay.setMaliyet(rs.getString("MALIYET"));
					detay.setTesteTeslimTarihi(rs.getDate("TTARIH").toString());
					detay.setTestKullanici(rs.getString("TKULLANICI"));
					detay.setGelistirmeTarihi(rs.getDate("GTARIH").toString());
					detay.setGuncelKullanici(rs.getString("GKULLANICI"));
					detay.setSurumId(rs.getString("SURUM_ID"));
					detay.setDefectId(rs.getString("DEFECT_ID"));

				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		model.addAttribute("surumId", detay.getSurumId());
		model.addAttribute("defectId", detay.getDefectId());
		model.addAttribute("jiraLink", detay.getJiraLink());
		model.addAttribute("baslik", detay.getBaslik());
		model.addAttribute("aciklama", detay.getAciklama());
		model.addAttribute("maliyet", detay.getMaliyet());
		model.addAttribute("testeTeslimTarihi", detay.getTesteTeslimTarihi());
		model.addAttribute("testKullanici", detay.getTestKullanici());
		model.addAttribute("gelistirmeTarihi", detay.getGelistirmeTarihi());
		model.addAttribute("guncelKullanici", detay.getGuncelKullanici());

		return "updateDetail";
	}

	@RequestMapping(value = "/detaysil", method = RequestMethod.GET) // deleting detail record
	public String detaySil(HttpServletRequest request, Detay detay,
			ModelMap model) {
		String contactId = request.getParameter("ID");
		count2 = Integer.parseInt(request.getParameter("cn"));

		if (contactId.isEmpty()) {

			if (count2 == detaylistekle.size()) {

			} else {
				for (int i = detaylistekle.size(); i > count2; i--) {
					detaylistekle.get(i - 1).setCount(
							detaylistekle.get(i - 2).getCount());

				}
			}

			detaylistekle.remove(count2 - 1);
			count--;
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/etiya", "root", "");
			
				String sql = "UPDATE XDSL_SURUM_DETAY SET ACTIVE=?  WHERE ID=?";
				PreparedStatement ps =con.prepareStatement(sql);
				ps.setString(1, "0");
				ps.setString(2, contactId);
				ps.executeUpdate();

				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}
		}

		ekle = true;
		detayliste(request, model);
		return "detail";
	}

}
