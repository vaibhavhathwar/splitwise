import axios from "axios";

const BASE_API = "http://localhost:8080/api/auth";

export const checkUserExist = async (email: string): Promise<boolean> => {
  try {
    const res = await axios.get(`${BASE_API}/userexist`, { params: { email } });
    return res.data;
  } catch (err) {
    throw err;
  }
};
