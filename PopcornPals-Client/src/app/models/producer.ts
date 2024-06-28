import { Media } from "./media";

export interface Producer {
    id: number,
    name: string,
    description: string,
    birthdate: Date,
    producedMedia: Media[]
}