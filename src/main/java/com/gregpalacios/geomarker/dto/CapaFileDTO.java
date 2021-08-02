package com.gregpalacios.geomarker.dto;

import com.gregpalacios.geomarker.model.Capa;
import com.gregpalacios.geomarker.util.FileDTO;

public class CapaFileDTO {

	private Capa capa;
	
	private FileDTO fileDTO;

	public Capa getCapa() {
		return capa;
	}

	public void setCapa(Capa capa) {
		this.capa = capa;
	}

	public FileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}
}
