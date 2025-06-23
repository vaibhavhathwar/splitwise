import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Card } from "@/components/ui/card";
const ActivityCard = () => {
  return (
    <Card className="p-4 w-100">
      <div className="flex gap-4 items-center">
        <Avatar>
          <AvatarImage
            src="https://github.com/shadcn.png"
            alt="@shadcn"
            className="rounded-full w-10 h-10"
          />
          <AvatarFallback>{""}</AvatarFallback>
        </Avatar>
        <div className="flex flex-col">
          <span className="font-semibold">You paid ₹500 to Ravi</span>
          <span className="text-sm text-muted-foreground">2 days ago</span>
        </div>
      </div>
    </Card>
  );
};

export default ActivityCard;
