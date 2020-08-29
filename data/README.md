# Coursework 2 Datasets

This data involves the analysis of meteorological data collected
over a number of years [from weather stations around the UK][1].  The files
`bradford.txt`, `durham.txt`, `lerwick.txt`, `oxford.txt` and `sheffield.txt`
are cleaned-up versions of five of these datasets.

Each file has the same format, consisting of a four-line **header** and then
a series of **records**, each containing data for a single month.  The fields
in a record are year, month, mean daily maximum temperature (degrees Celsius),
mean daily minimum temperature (degrees Celsius), days of air frost,
rainfall (mm) and number of hours of sunshine.  

Five other files are provided to help test the application:

* `bad-file.txt` contains only a single line.

* `bad-year.txt` contains a valid header plus a single record in which
  the year is invalid, where invalid is defined as 'earlier than 1930'.

* `bad-month.txt` contains a valid header plus a single record in
  which the month is invalid - i.e., outside the range 1-12.

* `bad-rainfall.txt` contains a valid header plus a single record in
  which the rainfall value is invalid - i.e., negative.

* `header-only.txt` contains header lines
but no data.

[1]: http://www.metoffice.gov.uk/public/weather/climate-historic/
