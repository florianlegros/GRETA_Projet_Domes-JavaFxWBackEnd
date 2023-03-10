package entity;

import java.util.List;

public class Commande {
	private int id;
	private String date;
	private String adresse;
	private String status;
	private Article article;

	public Commande() {
	}

	public Commande(String date, String adresse, String status, Article article) {
		this.date = date;
		this.adresse = adresse;
		this.status = status;
		this.article = article;
	}

	public Commande(int id, String date, String adresse, String status, Article article) {
		this.id = id;
		this.date = date;
		this.adresse = adresse;
		this.status = status;
		this.article = article;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + ", adresse=" + adresse + ", status=" + status + "\n article="
				+ article + "]";
	}

}
