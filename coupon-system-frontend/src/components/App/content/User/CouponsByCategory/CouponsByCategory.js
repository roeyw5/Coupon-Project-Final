import history from "../../../../App/history";
import "./coupons-by-category.css";

function CouponsByCategory(props) {
  const redirect = () => {
    if (history.location.pathname.startsWith("/company"))
      history.push("/company/my-coupons");
    else history.push("/customer/my-coupons");
  };

  if (props.coupons[0].id === "") redirect();

  const buttonDisabled = {
    attraction: true,
    extreme: true,
    exploring: true,
    business: true,
    party: true,
    restaurant: true,
    sport: true,
    vacation: true,
  };

  for (let coupon of props.coupons) {
    switch (coupon.category) {
      case "ATTRACTION":
        buttonDisabled.attraction = false;
        break;
      case "EXTREME":
        buttonDisabled.extreme = false;
        break;
      case "EXPLORING":
        buttonDisabled.exploring = false;
        break;
      case "BUSINESS":
        buttonDisabled.business = false;
        break;
      case "PARTY":
        buttonDisabled.party = false;
        break;
      case "RESTAURANT":
        buttonDisabled.restaurant = false;
        break;
      case "SPORT":
        buttonDisabled.sport = false;
        break;
      case "VACATION":
        buttonDisabled.vacation = false;
        break;
      default:
        break;
    }
  }

  const handleButton = (category) => {
    props.handleSearchCategory(category);
    redirect();
  };

  return (
    <>
      <div className="CouponsByCategory-background"></div>
      <div className="CouponsByCategory">
        <div className="CouponsByCategory-close-div">
          <button
            className="btn-close"
            aria-label="Close"
            id="CouponsByCategory-close-button"
            onClick={redirect}
            title="Close"
          ></button>
        </div>
        <h5
          className="CouponsByCategory-title"
          title="Search coupons by the category..."
        >
          Search coupons by the category...
        </h5>
        <div className="CouponsByCategory-div-title-seperator"></div>
        <div>
          <button
            className="btn btn-primary"
            title="attraction"
            id="CouponsByCategory-button"
            onClick={() => handleButton("ATTRACTION")}
            disabled={buttonDisabled.attraction}
          >
            Attraction
          </button>
          <button
            className="btn btn-primary"
            title="Extreme"
            id="CouponsByCategory-button"
            onClick={() => handleButton("EXTREME")}
            disabled={buttonDisabled.extreme}
          >
            Extreme
          </button>
          <button
            className="btn btn-primary"
            title="Exploring"
            id="CouponsByCategory-button"
            onClick={() => handleButton("EXPLORING")}
            disabled={buttonDisabled.exploring}
          >
            Exploring
          </button>
          <button
            className="btn btn-primary"
            title="Business"
            id="CouponsByCategory-button"
            onClick={() => handleButton("BUSINESS")}
            disabled={buttonDisabled.business}
          >
            Business
          </button>
          <button
            className="btn btn-primary"
            title="Party"
            id="CouponsByCategory-button"
            onClick={() => handleButton("PARTY")}
            disabled={buttonDisabled.party}
          >
            Party
          </button>
          <button
            className="btn btn-primary"
            title="Restaurant"
            id="CouponsByCategory-button"
            onClick={() => handleButton("RESTAURANT")}
            disabled={buttonDisabled.restaurant}
          >
            Restaurant
          </button>
          <button
            className="btn btn-primary"
            title="Sport"
            id="CouponsByCategory-button"
            onClick={() => handleButton("SPORT")}
            disabled={buttonDisabled.sport}
          >
            Sport
          </button>
          <button
            className="btn btn-primary"
            title="Vacation"
            id="CouponsByCategory-button"
            onClick={() => handleButton("VACATION")}
            disabled={buttonDisabled.vacation}
          >
            Vacation
          </button>
        </div>
        <div className="CouponsByCategory-div-seperator"></div>
        <div className="CouponsByCategory-div-seperator"></div>
      </div>
    </>
  );
}

export default CouponsByCategory;
