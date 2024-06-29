import { Media } from "./media";
import { User } from "./user";

export interface Playlist {
    id?: number,
    name?: string,
    creator?: User,
    content?: Media[];
}