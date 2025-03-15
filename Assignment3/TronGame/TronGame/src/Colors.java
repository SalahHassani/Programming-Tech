public enum Colors {
  RED("#FF0000"),
  BLUE("#0000FF"),
  LIGHT_RED("#FF8777"),
  LIGHT_BLUE("#ADD8E6");

  private final String colorCode;

  // Constructor
  Colors(String colorCode) {
    this.colorCode = colorCode;
  }

  public String getColorCode() {
    return colorCode;
  }
}
