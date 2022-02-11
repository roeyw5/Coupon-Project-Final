import { Fragment } from "react";
import homeImage from "./../../../../../../assests/Logo.png";

function AdminHome() {
  return (
    <Fragment>
      <img
        className="user-home-image"
        src={homeImage}
        alt="home png"
        title="Space Tours Coupons"
      ></img>
    </Fragment>
  );
}

export default AdminHome;
