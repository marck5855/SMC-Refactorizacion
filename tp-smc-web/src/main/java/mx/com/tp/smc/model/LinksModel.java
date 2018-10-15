
package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.Link;

public class LinksModel extends MainModel {

	private ArrayList<Link> links;

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "LinksModel [links=" + links + "]";
	}

}
