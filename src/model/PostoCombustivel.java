package model;

import java.util.ArrayList;
import java.util.List;

public class PostoCombustivel {
	
	List<BombaCombustivel> listaBombasPosto;

	public PostoCombustivel(List<BombaCombustivel> listaBombasPosto) {
		super();
		this.listaBombasPosto = listaBombasPosto;
	}

	public List<BombaCombustivel> getListaBombasPosto() {
		return listaBombasPosto;
	}

	public void setListaBombasPosto(List<BombaCombustivel> listaBombasPosto) {
		this.listaBombasPosto = listaBombasPosto;
	}

	@Override
	public String toString() {
		return "PostoCombustivel [listaBombasPosto=" + listaBombasPosto + "]";
	}
	
}
