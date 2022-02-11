import history from "./../../../../../history";
import { Redirect, Route, Switch } from "react-router-dom";
import "../../../../../../../styles/action-sidebar.css";
import CustomerService from "../../../../../../../services/user-services/CustomerService";
import AuthenticationService from "../../../../../../../services/AuthenticationService";
import CouponsByCategory from "../../../CouponsByCategory/CouponsByCategory";
import { isMinimum } from "../../../../../../../utils/checkUtil";

function CustomerCouponActionbar(props) {
  const handleAllCoupons = () => {
    CustomerService.getCoupons().then(
      (result) => {
        props.setCoupons(result.data);
      },
      (error) => {
        try {
          if (error.response.data.response) {
            setTimeout(() => {
              alert("You do not own any coupons.");
            }, 0);
            return;
          }
          if (error.response) {
            setTimeout(() => {
              alert("Your session has expired, please login");
            }, 0);
            AuthenticationService.logOut();
          }
        } catch {
          setTimeout(() => {
            alert("Server not responding, try again later.");
          }, 0);
          AuthenticationService.logOut();
        }
      }
    );
  };

  const handleSearchCategoryPush = () => {
    handleAllCoupons();
    history.push("/customer/my-coupons/search-by-category");
  };

  const handleSearchCategory = (category) => {
    CustomerService.getCouponsCategory(category).then(
      (result) => {
        props.setCoupons(result.data);
      },
      (error) => {
        try {
          if (error.response.data.response) {
            setTimeout(() => {
              alert(
                'No coupons with the category of "' + category + '" were found.'
              );
            }, 0);
            return;
          }
          if (error.response) {
            setTimeout(() => {
              alert("Your session has expired, please login");
            }, 0);
            AuthenticationService.logOut();
          }
        } catch {
          setTimeout(() => {
            alert("Server not responding, try again later.");
          }, 0);
          AuthenticationService.logOut();
        }
      }
    );
  };

  const handleSearchPrice = () => {
    const userOption = prompt("Please enter a maximum price to filter by");
    if (isMinimum(userOption, 0))
      CustomerService.getCouponsPrice(userOption).then(
        (result) => {
          props.setCoupons(result.data);
        },
        (error) => {
          try {
            if (error.response.data.response) {
              setTimeout(() => {
                alert(
                  'No coupons with the maximum price of "' +
                    userOption +
                    '" were found.'
                );
              }, 0);
              return;
            }
            if (error.response) {
              setTimeout(() => {
                alert("Your session has expired, please login");
              }, 0);
              AuthenticationService.logOut();
            }
          } catch {
            setTimeout(() => {
              alert("Server not responding, try again later.");
            }, 0);
            AuthenticationService.logOut();
          }
        }
      );
  };

  return (
    <>
      <div className="CustomerCouponActionbar">
        <div className="action-sidebar">
          <button
            className="action-sidebar-button"
            onClick={handleAllCoupons}
            title="All Coupons"
          >
            Show All
          </button>
          <div className="action-sidebar-button-seperator"></div>
          <button
            className="action-sidebar-button"
            onClick={handleSearchCategoryPush}
            title="Filter by Category"
          >
            Filter by Category
          </button>
          <div className="action-sidebar-button-seperator"></div>
          <button
            className="action-sidebar-button"
            onClick={handleSearchPrice}
            title="Filter by Price"
          >
            Filter by Price
          </button>
        </div>
      </div>
      <Switch>
        <Route path="/customer/my-coupons" exact></Route>
        <Route path={"/customer/my-coupons/search-by-category"}>
          <CouponsByCategory
            handleSearchCategory={handleSearchCategory}
            coupons={props.coupons}
          />
        </Route>
        <Route path={"/customer/my-coupons"}>
          <Redirect to="/customer/home" />
        </Route>
      </Switch>
    </>
  );
}

export default CustomerCouponActionbar;
