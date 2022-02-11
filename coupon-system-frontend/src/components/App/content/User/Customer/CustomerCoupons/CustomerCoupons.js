import CustomerCouponActionbar from "./CustomerCouponActionbar/CustomerCouponActionbar";
import CustomerService from "../../../../../../services/user-services/CustomerService";
import "./CustomerCoupons.css";
import { Fragment, useEffect, useState } from "react";
import AuthenticationService from "../../../../../../services/AuthenticationService";

function CustomerCoupons() {
  const [coupons, setCoupons] = useState([
    {
      id: "",
      category: "",
      description: "",
      endDate: "",
      startDate: "",
      price: "",
      image: "",
      title: "",
    },
  ]);

  useEffect(() => {
    CustomerService.getCoupons().then(
      (result) => {
        setCoupons(result.data);
      },
      (error) => {
        try {
          if (error.response.data.response) {
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
  }, []);

  const getCoupons = () => {
    if (coupons[0].id === "") return;
    return coupons.map((stream, index) => {
      return (
        <>
          <tr key={index}>
            <input id={stream.id} className="CustomerCoupons-input" />
            <td></td>
            <td className="CustomerCoupons-table-image">
              <div
                className="CustomerCoupons-table-image-div"
                style={{ backgroundImage: "url(" + stream.image + ")" }}
              >
                <label
                  htmlFor={stream.id}
                  className="CustomerCoupons-table-text-label"
                ></label>
              </div>
            </td>
            <td>
              <label
                htmlFor={stream.id}
                className="CustomerCoupons-table-text-label"
                title={`TITLE: ${stream.title}`}
              >
                {stream.title}
              </label>
            </td>
            <td>
              <label
                htmlFor={stream.id}
                className="CustomerCoupons-table-text-label"
                id="CustomerCoupons-table-description"
                title={`DESCRIPTION: ${stream.description}`}
              >
                {stream.description}
              </label>
            </td>
            <td>
              <label
                htmlFor={stream.id}
                className="CustomerCoupons-table-text-label"
                title={`PRICE: $${stream.price}`}
              >
                ${stream.price}
              </label>
            </td>
            <td>
              <label
                htmlFor={stream.id}
                className="CustomerCoupons-table-text-label"
                title={`CATEGORY: ${stream.category}`}
                id="CustomerCoupons-table-category"
              >
                {stream.category}
              </label>
            </td>
            <td>
              <label
                htmlFor={stream.id}
                className="CustomerCoupons-table-text-label"
                title={`RELEASE DATE: ${stream.startDate}`}
              >
                {stream.startDate}
              </label>
            </td>
            <td>
              <label
                htmlFor={stream.id}
                className="CustomerCoupons-table-text-label"
                title={`EXPIRATION DATE: ${stream.endDate}`}
              >
                {stream.endDate}
              </label>
            </td>
          </tr>
          <tr className="CustomerCoupons-table-tr-seperator"></tr>
        </>
      );
    });
  };

  return (
    <Fragment className="CustomerCoupons">
      <CustomerCouponActionbar coupons={coupons} setCoupons={setCoupons} />
      <table className="CustomerCoupons-table">
        <tbody>
          <tr>
            <th></th>
            <th className="CustomerCoupons-table-header" width="6%"></th>
            <th
              className="CustomerCoupons-table-header"
              width="20%"
              title="Title"
            >
              Title
            </th>
            <th
              className="CustomerCoupons-table-header"
              width="37%"
              title="Description"
            >
              Description
            </th>
            <th
              className="CustomerCoupons-table-header"
              width="9%"
              title="Price"
            >
              Price
            </th>
            <th
              className="CustomerCoupons-table-header"
              width="10%"
              title="Category"
            >
              Category
            </th>
            <th
              className="CustomerCoupons-table-header"
              width="9%"
              title="Release Date"
            >
              Release Date
            </th>
            <th
              className="CustomerCoupons-table-header"
              width="9%"
              title="Expiration Date"
            >
              Expiration Date
            </th>
          </tr>
          {getCoupons()}
        </tbody>
      </table>
    </Fragment>
  );
}

export default CustomerCoupons;
