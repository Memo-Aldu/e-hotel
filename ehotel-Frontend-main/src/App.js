import Register from './components/Form/RegisterForm';
import Login from "./components/Form/LoginForm";
import Home from './pages/home/Home';
import Layout from './components/Layout';
import Employee from './features/auth/Employee';
import Missing from './pages/auth/Missing';
import Unauthorized from './pages/auth/Unauthorized';
import Lounge from './pages/employee/Lounge';
import LinkPage from './pages/LinkPage';
import RequireAuth from './features/auth/RequireAuth';
import { Routes, Route } from 'react-router-dom';
import roles from "./roles"
import styles from './App.css'
import AddHotel from './components/modifications/Add/AddHotel';
import AddStay from './components/modifications/Add/AddStay';
import AddEmployee from './components/modifications/Add/AddEmployee';
import AddCustomer from './components/modifications/Add/AddCustomer';
import AddRoom from './components/modifications/Add/AddRoom';
import { HotelList } from "./components/modifications/List/HotelList"

function App() {

  return (
    <Routes>
        <Route path="/" element={<Layout/>}>
          {/* public routes */}
          <Route index element={<Home />}/>
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="linkpage" element={<LinkPage />} />
          <Route path="unauthorized" element={<Unauthorized />} />

          {/* protected routes */}
          <Route element={<RequireAuth allowedRoles={[roles.EMPLOYEE]}/>}>
            <Route path="employee" element={<Employee/>} />
          <Route path="lounge" element={<Lounge />} />            
          <Route path="addHotel" element={<AddHotel/>} />
          <Route path="addStay" element={<AddStay/>} />
          <Route path="addEmployee" element={<AddEmployee />} />
          <Route path="addCustomer" element={<AddCustomer/>} />
          <Route path="addRoom" element={<AddRoom/>} />
          <Route path="deleteHotel" element={<HotelList/>} />
       {/*    <Route path="addCustomer" element={<CustomerForm />} />
          <Route path="addHotelChain" element={<HotelChainForm/>} />
          <Route path="addEmployee" element={<EmployeeForm />} />*/}
          </Route>
        {/* catch all */}
{/*         <Route path="*" element={<Missing />} /> */}
      </Route>
    </Routes>
  );
}

export default App;