insert into TILDA.ZoneInfo ("id", "label", "value", "deactivatedTZ", "deactivated", "created", "lastUpdated")
   values ('UTC'  , 'UTC'                , 'Etc/UTC'            , null, null, current_timestamp, current_timestamp)
         ,('Zulu' , 'Zulu'               , 'Etc/Zulu'           , null, null, current_timestamp, current_timestamp)
         ,('Z'    , 'Z'                  , 'Z'                  , null, null, current_timestamp, current_timestamp)
         ,('Green', 'Greenwich'          , 'Etc/Greenwich'      , null, null, current_timestamp, current_timestamp)
         ,('CaNe' , 'Canada Newfoundland', 'Canada/Newfoundland', null, null, current_timestamp, current_timestamp)
         ,('CaAt' , 'Canada Atlantic'    , 'Canada/Atlantic'    , null, null, current_timestamp, current_timestamp)
         ,('USEa' , 'US Eastern'         , 'US/Eastern'         , null, null, current_timestamp, current_timestamp)
         ,('AmNYC', 'New York City'      , 'America/New_York'   , null, null, current_timestamp, current_timestamp)
         ,('CaEa' , 'Canada Eastern'     , 'Canada/Eastern'     , null, null, current_timestamp, current_timestamp)
         ,('USCe' , 'US Central'         , 'US/Central'         , null, null, current_timestamp, current_timestamp)
         ,('CaCe' , 'Canada Central'     , 'Canada/Central'     , null, null, current_timestamp, current_timestamp)
         ,('CaSa' , 'Canada Saskatchewan', 'Canada/Saskatchewan', null, null, current_timestamp, current_timestamp)
         ,('USAr' , 'US Arizona'         , 'US/Arizona'         , null, null, current_timestamp, current_timestamp)
         ,('USMo' , 'US Mountain'        , 'US/Mountain'        , null, null, current_timestamp, current_timestamp)
         ,('CaMo' , 'Canada Mountain'    , 'Canada/Mountain'    , null, null, current_timestamp, current_timestamp)
         ,('USPa' , 'US Pacific'         , 'US/Pacific'         , null, null, current_timestamp, current_timestamp)
         ,('CaPa' , 'Canada Pacific'     , 'Canada/Pacific'     , null, null, current_timestamp, current_timestamp)
         ,('USAl' , 'US Alaska'          , 'US/Alaska'          , null, null, current_timestamp, current_timestamp)
         ,('USHa' , 'US Hawaii'          , 'US/Hawaii'          , null, null, current_timestamp, current_timestamp)
         ,('USSa' , 'US Samoa'           , 'US/Samoa'           , null, null, current_timestamp, current_timestamp)
 on conflict do nothing;
