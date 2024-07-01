import { Media } from "./media";
import { User } from "./user";

export interface Review {
    id?: number,
    rating?: number,
    description?: string,
    date?: Date,
    media?: Media,
    creator?: User
}