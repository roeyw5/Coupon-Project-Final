/*checks if the entered value is a number, returns true if its a number and atleast the minimum*/
export const isMinimum = (value, min) => {
  if (value === null) return false;
  if (isNaN(value) || value.includes(" ") || value === "") {
    setTimeout(() => {
      alert(
        "The entered value was not a number, please enter a numeric value."
      );
    }, 0);
    return false;
  }
  if (value < min) {
    setTimeout(() => {
      alert("The value must be atleast " + min + ".");
    }, 0);
    return false;
  }
  return true;
};


