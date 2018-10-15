package mx.com.tp.smc.model;

import java.util.ArrayList;

import mx.com.tp.smc.entity.Comment;

public class CommentModel extends MainModel{

	private ArrayList<Comment> comentario;


	public ArrayList<Comment> getComentario() {
		return comentario;
	}

	public void setComentario(ArrayList<Comment> comentario) {
		this.comentario = comentario;
	}
	
}
