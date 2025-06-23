import ActivityCard from "./components/ActivityCard";
import CustomOweCard from "./components/CustomOweCard";

const Home = () => {
  return (
    <div className="flex flex-col items-center">
      <div className="flex justify-between gap-5 border rounded-2xl p-2">
        <div className="border rounded-2xl w-full p-2">
          <h1 className="text-center text-xl font-bold pb-2">You Owe</h1>
          <div className="flex flex-wrap gap-2 justify-between max-h-80 overflow-y-auto">
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
            <CustomOweCard userName="Ravi" isIn={false} amount="1232" />
          </div>
        </div>
        <div className="border rounded-2xl w-full p-2">
          <h1 className="text-center text-xl font-bold pb-2">You Are Owed</h1>
          <div className="flex flex-wrap gap-2 justify-between max-h-80 overflow-y-auto">
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
            <CustomOweCard userName="Manu" isIn={true} amount="12000" />
          </div>
        </div>
      </div>
      <div className="border rounded-2xl p-2 min-w-200">
        <h1 className="font-bold text-center text-2xl">Activities</h1>
        <div className="flex flex-wrap gap-2 justify-center">
          <ActivityCard />
          <ActivityCard />
          <ActivityCard />
          <ActivityCard />
          <ActivityCard />
        </div>
      </div>
    </div>
  );
};

export default Home;
