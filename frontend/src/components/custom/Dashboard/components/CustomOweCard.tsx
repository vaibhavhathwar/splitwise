import { AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import type { CustomOweCardProps } from "@/types/PropTypes";
import { Avatar } from "@radix-ui/react-avatar";

const CustomOweCard = ({ userName, isIn, amount }: CustomOweCardProps) => {
  return (
    <div>
      <Card className="gap-1 py-3 w-fit">
        <CardHeader className="grid-cols-2 items-center pb-0">
          <Avatar>
            <AvatarImage
              src="https://github.com/shadcn.png"
              alt="@shadcn"
              className="rounded-full w-10 h-10"
            />
            <AvatarFallback>{userName}</AvatarFallback>
          </Avatar>
          <CardTitle>{userName}</CardTitle>
        </CardHeader>
        <CardContent>
          <span className={isIn ? "text-green-500" : "text-red-500"}>
            {isIn ? `Owes you ${amount} Rs.` : `You owe ${amount} Rs.`}
          </span>
        </CardContent>
      </Card>
    </div>
  );
};

export default CustomOweCard;
