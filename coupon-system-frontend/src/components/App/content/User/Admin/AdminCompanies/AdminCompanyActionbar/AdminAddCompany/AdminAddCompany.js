import "./admin-add-company.css";
import history from "../../../../../../history";
import { Formik, Form, Field } from "formik";
import AdminService from "../../../../../../../../services/user-services/AdminService";
import AuthenticationService from "../../../../../../../../services/AuthenticationService";

function AdminAddCompany(props) {
  const closeDiv = () => {
    if (window.confirm("Are you sure you want to close this window?"))
      history.push("/admin/companies");
  };

  const handleSubmit = (values) => {
    AdminService.addCompany(values.name, values.email, values.password).then(
      () => {
        setTimeout(() => {
          alert("The company was added successfully.");
        }, 0);
        history.push("/admin/companies");
        props.handleAllCompanies();
      },
      (error) => {
        try {
          if (error.response.data.response) {
            if (
              error.response.data.response.includes("email") ||
              error.response.data.errorCode === "USR-006.001"
            ) {
              setTimeout(() => {
                alert(
                  'A user with the email "' +
                    values.email +
                    '" already exists,\nPlease choose a different email.'
                );
              }, 0);
            } else if (error.response.data.response.includes("name"))
              setTimeout(() => {
                alert(
                  'A company with the name "' +
                    values.name +
                    '" already exists,\nPlease choose a different name.'
                );
              }, 0);
            else {
              setTimeout(() => {
                alert("Action Failed\n" + error.response.data.response);
              }, 0);
            }
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
      <div className="AdminAddCompany-background"></div>
      <div className="AdminAddCompany">
        <div className="AdminAddCompany-close-div">
          <button
            className="btn-close"
            aria-label="Close"
            id="AdminAddCompany-close-button"
            onClick={closeDiv}
            title="Close"
          ></button>
        </div>
        <h5 className="AdminAddCompany-title" title="Add A New Company">
          Add A New Company
        </h5>
        <Formik
          initialValues={{
            name: "",
            email: "",
            password: "",
          }}
          onSubmit={handleSubmit}
        >
          {({ values, handleChange }) => (
            <Form>
              <div className="AdminAddCompany-div-seperator"></div>
              <div className="input-group">
                <div
                  className="input-group-text"
                  id="AdminAddCompany-name-div"
                  title="Name"
                >
                  Name
                </div>
                <Field
                  title={values.name}
                  name="name"
                  className="form-control"
                  placeholder="Name"
                  required={true}
                  id="AdminAddCompany-name-input"
                />
              </div>
              <div className="AdminAddCompany-div-seperator"></div>
              <div className="input-group">
                <div
                  className="input-group-text"
                  id="AdminAddCompany-email-div"
                  title="Email"
                >
                  Email
                </div>
                <input
                  onChange={handleChange}
                  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                  type="email"
                  title={values.email}
                  name="email"
                  className="form-control"
                  placeholder="Email"
                  required={true}
                  autoComplete="true"
                  id="AdminAddCompany-email-input"
                />
              </div>
              <div className="AdminAddCompany-div-seperator"></div>
              <div className="input-group">
                <div
                  className="input-group-text"
                  id="AdminAddCompany-password-div"
                  title="Password"
                >
                  Password
                </div>
                <Field
                  type="password"
                  name="password"
                  className="form-control"
                  placeholder="Password"
                  required={true}
                  autoComplete="true"
                  id="AdminAddCompany-password-input"
                />
              </div>
              <div className="AdminAddCompany-div-seperator"></div>
              <div className="AdminAddCompany-div-seperator"></div>
              <div className="AdminAddCompany-submit">
                <button
                  type="submit"
                  className="btn btn-primary"
                  title="Add Company"
                >
                  Add Company
                </button>
              </div>
              <div className="AdminAddCompany-div-seperator"></div>
              <div className="AdminAddCompany-div-seperator"></div>
            </Form>
          )}
        </Formik>
      </div>
    </>
  );
}

export default AdminAddCompany;
