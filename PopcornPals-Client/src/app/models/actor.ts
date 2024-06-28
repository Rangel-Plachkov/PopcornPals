import { Media } from "./media";

export interface Actor {
  id?: number,
  name?: string,
  description?: string,
  birthdate?: Date,
  starsIn?: Media[]
}
