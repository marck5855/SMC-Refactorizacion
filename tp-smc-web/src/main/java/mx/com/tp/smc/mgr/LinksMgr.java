
package mx.com.tp.smc.mgr;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.tp.smc.entity.Link;
import mx.com.tp.smc.entity.Role;
import mx.com.tp.smc.manager.LinkManager;
import mx.com.tp.smc.model.LinksModel;
import mx.com.tp.smc.model.RoleModel;
import mx.com.tp.smc.request.LinkAdd;
import mx.com.tp.smc.response.LinkList;
import mx.com.tp.smc.response.ResponseLink;
import mx.com.tp.smc.service.LinksService;
import mx.com.tp.smc.service.TokenService;

@Component
public class LinksMgr {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private LinksService linksService;
	
//	Marco
	@Autowired
	private LinkManager linkManager;

	public LinksModel getLinks(String organization) {
		LinksModel model = new LinksModel();
		try {
			LinkList responseManager = linkManager.returnOrganizacionLinks(organization);
			if ((Boolean) responseManager.getSuccess()) {

				ArrayList<Link> links = new ArrayList<Link>();

				for (mx.com.tp.smc.response.Link linksr : responseManager.getListLinks()) {

					Link link = new Link();

					if (linksr.getIdLink() != null)
						link.setIdLink((Long)linksr.getIdLink());
					if (linksr.getUrl() != null)
						link.setUrl((String) linksr.getUrl());
					if (linksr.getNombreLink() != null)
						link.setNombreLink((String) linksr.getNombreLink());
					if (linksr.getDescripcionLink() != null)
						link.setDescripcionLink((String) linksr.getDescripcionLink());

					links.add(link);

				}
				model.setLinks(links);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public LinksModel getLinks(String organization) {
//		LinksModel model = new LinksModel();
//		try {
//
//			JSONObject json = linksService.getLinks(tokenService.getToken("adri", "adri"), organization);
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listLinks");
//
//				ArrayList<Link> links = new ArrayList<Link>();
//
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					Link link = new Link();
//
//					if (object.get("idLink") != null)
//						link.setIdLink((Long) object.get("idLink"));
//					if (object.get("url") != null)
//						link.setUrl((String) object.get("url"));
//					if (object.get("nombreLink") != null)
//						link.setNombreLink((String) object.get("nombreLink"));
//					if (object.get("descripcionLink") != null)
//						link.setDescripcionLink((String) object.get("descripcionLink"));
//
//					links.add(link);
//
//				}
//				model.setLinks(links);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marco
	public LinksModel getLinks() {
		LinksModel model = new LinksModel();
		try {

			LinkList responseManager = linkManager.returnAllLinks();
//			JSONObject json = linksService.getLinks(tokenService.getToken("adri", "adri"));
			if ((Boolean) responseManager.getSuccess()) {
//				JSONArray array = (JSONArray) json.get("listLinks");

				ArrayList<Link> links = new ArrayList<Link>();

//				for (int i = 0; i < array.size(); i++) {
				for (mx.com.tp.smc.response.Link linkr :responseManager.getListLinks()) {
//					JSONObject object = (JSONObject) array.get(i);
					Link link = new Link();

					if (linkr.getIdLink() != null)
						link.setIdLink((Long) linkr.getIdLink());
					if (linkr.getUrl() != null)
						link.setUrl((String) linkr.getUrl());
					if (linkr.getNombreLink() != null)
						link.setNombreLink((String) linkr.getNombreLink());
					if (linkr.getDescripcionLink() != null)
						link.setDescripcionLink((String) linkr.getDescripcionLink());

					links.add(link);

				}
				model.setLinks(links);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public LinksModel getLinks() {
//		LinksModel model = new LinksModel();
//		try {
//
//			JSONObject json = linksService.getLinks(tokenService.getToken("adri", "adri"));
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listLinks");
//
//				ArrayList<Link> links = new ArrayList<Link>();
//
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					Link link = new Link();
//
//					if (object.get("idLink") != null)
//						link.setIdLink((Long) object.get("idLink"));
//					if (object.get("url") != null)
//						link.setUrl((String) object.get("url"));
//					if (object.get("nombreLink") != null)
//						link.setNombreLink((String) object.get("nombreLink"));
//					if (object.get("descripcionLink") != null)
//						link.setDescripcionLink((String) object.get("descripcionLink"));
//
//					links.add(link);
//
//				}
//				model.setLinks(links);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public LinksModel insertLinks(String nombreLink, String link, String organization,String descripcionLink) {


		LinksModel model = new LinksModel();
		LinkAdd request = new LinkAdd();
		request.setNombreLink(nombreLink);
		request.setLink(descripcionLink);
		request.setNombreLink(link);
		request.setOrganization(organization);
		try {
			ResponseLink responseManager = linkManager.insertLink(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<Link> links = new ArrayList<Link>();

				Link linkObj = new Link();
				linkObj.setMssg((String) responseManager.getMssg());
				linkObj.setSucces(String.valueOf(responseManager.getSuccess()));
				links.add(linkObj);

				model.setLinks(links);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public LinksModel insertLinks(String nombreLink, String link, String organization,String descripcionLink) {
//
//		// System.out.println("insertLinks: rol: " + rol + "descripcion: " +
//		// descripcion + "organization: " + idOrganizacion);
//
//		LinksModel model = new LinksModel();
//		try {
//			JSONObject json = linksService.insertLinks(tokenService.getToken("adri", "adri"), nombreLink, link,
//					organization,descripcionLink);
//			if ((Boolean) json.get("success")) {
//				ArrayList<Link> links = new ArrayList<Link>();
//
//				Link linkObj = new Link();
//				linkObj.setMssg((String) json.get("mssg"));
//				linkObj.setSucces((String) json.get("success").toString());
//				links.add(linkObj);
//
//				model.setLinks(links);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

	public RoleModel deleteRol(BigDecimal idRol) {
		RoleModel model = new RoleModel();
		try {
			JSONObject json = new JSONObject(); // rolService.deleteRol(tokenService.getToken("adri",
												// "adri"), idRol);
			if ((Boolean) json.get("success")) {
				ArrayList<Role> roles = new ArrayList<Role>();
				Role rol = new Role();
				rol.setMssg((String) json.get("mssg"));
				rol.setSucces((String) json.get("success").toString());
				roles.add(rol);
				model.setRoles(roles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}