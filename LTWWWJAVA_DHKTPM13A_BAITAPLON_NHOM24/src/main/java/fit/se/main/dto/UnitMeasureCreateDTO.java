package fit.se.main.dto;

public class UnitMeasureCreateDTO {
	private int unitId;
	private String untitName;
	public UnitMeasureCreateDTO(int unitId) {
		this.unitId = unitId;
	}
	public int getUnitId() {
		return unitId;
	}
	public String getUntitName() {
		return untitName;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public void setUntitName(String untitName) {
		this.untitName = untitName;
	}
	@Override
	public String toString() {
		return "UnitMeasureCreateDTO [unitId=" + unitId + ", untitName=" + untitName + "]";
	}
	
	
}
