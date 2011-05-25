package back;

public enum MoveTypes {
	UP(new Point(-1,0)),
	DOWN(new Point(1,0)),
	LEFT(new Point(0,-1)),
	RIGHT(new Point(0,1));
	
	private Point direction;

	private MoveTypes(Point p){
		this.direction=p;
	}
	
	public Point getDirection(){
		return direction;
	}
	
	public int x(){
		return direction.x;
	}
	
	public int y(){
		return direction.y;
	}

}
