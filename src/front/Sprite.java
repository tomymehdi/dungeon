package front;

import java.awt.Image;

import back.Point;

/**
 * Objeto que puede ser ubicado dentro de un {@code GamePanel}. Los sprites
 * tienen una imagen asociada (que puede ser modificada) y una posición
 * relativa al panel.
 */
public class Sprite {

	private Image image;
	private Point position;

	/**
	 * Crea un nuevo sprite ubicado en las coordenadas indicadas con alguna
	 * imagen asociada.
	 * 
	 * @param image
	 *            Imagen asociada al sprite.
	 * @param position
	 *            Ubicación del sprite.
	 */
	public Sprite(Image image, Point position) {
		this.image = image;
		this.position = position;
	}

	/**
	 * Retorna la imagen asociada al sprite.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Retorna la ubicación del sprite.
	 */
	public Point getPosition() {
		return position;
	}

	void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprite other = (Sprite) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	void changeImage(Image image) {
		this.image = image;
	}
}
