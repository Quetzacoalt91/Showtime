# ShowTime


## TV Channels update

Images in res/drawable fodler only accept lowercase a-z, 0-9 and underscores.
Before moving channels logo in this folder, renaming them was needed.

```
find . -type f -exec bash -c 'mv -- "$1" "${1// /_}"' bash {} \;
find . -type f -exec bash -c 'mv -- "$1" "${1//&/_}"' bash {} \;
find . -type f -exec bash -c 'mv -- "$1" "${1//+/_}"' bash {} \;
find . -type f -exec bash -c 'mv -- "$1" "${1//(/_}"' bash {} \;
find . -type f -exec bash -c 'mv -- "$1" "${1//)/_}"' bash {} \;
find . -type f -exec bash -c 'mv -- "$1" "${1//!/_}"' bash {} \;
```
