import axios from "axios";

axios.interceptors.request.use((config) => {
  const user = JSON.parse(localStorage.getItem("user"));
  if (user && user.token) {
    const token = "Bearer " + user.token;
    config.headers.Authorization = token;
  }
  return config;
});

class AdminService {
  async addCustomer(firstName, lastName, email, password) {
    return await axios.post("http://localhost:8080/admin/add-customer", {
      firstName,
      lastName,
      email,
      password,
    });
  }

  async updateCustomer(id, firstName, lastName, email, password) {
    return await axios.put("http://localhost:8080/admin/update-customer", {
      id,
      firstName,
      lastName,
      email,
      password,
    });
  }

  async deleteCustomer(id) {
    return await axios.delete(
      "http://localhost:8080/admin/delete-customer?customerId=" + id
    );
  }

  async getCustomers() {
    return await axios.get("http://localhost:8080/admin/get-customers");
  }

  async getCustomer(id) {
    return await axios.get("http://localhost:8080/admin/get-customer?id=" + id);
  }

  async addCompany(name, email, password) {
    return await axios.post("http://localhost:8080/admin/add-company", {
      name,
      email,
      password,
    });
  }

  async updateCompany(id, name, email, password) {
    return await axios.put("http://localhost:8080/admin/update-company", {
      id,
      name,
      email,
      password,
    });
  }

  async deleteCompany(id) {
    return await axios.delete(
      "http://localhost:8080/admin/delete-company?companyId=" + id
    );
  }

  async getCompanies() {
    return await axios.get("http://localhost:8080/admin/get-companies");
  }

  async getCompany(id) {
    return await axios.get("http://localhost:8080/admin/get-company?id=" + id);
  }

  async getCoupons() {
    return await axios.get("http://localhost:8080/admin/get-coupons");
  }
}

export default new AdminService();
